package kr.ac.sch.whitehac97.program.explorer;

import kr.ac.sch.whitehac97.program.service.FileServiceMonitor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class ExplorerBase
{
    /**
     * 특별한 경로 지정 없이 클래스를 생성합니다.
     */
    public ExplorerBase() { }

    /**
     * 탐색 기준이 되는 경로를 미리 설정한 후 클래스를 생성합니다.
     * @param targetPath 탐색의 기준이 되는 경로
     */
    public ExplorerBase(String targetPath) { this.currentPath = targetPath; }

    /**
     * 작업이 진행되고 있는 경로입니다. 초기에 탐색 과정을 진행하기 전, 탐색이 진행되기 위한 경로의 기준이 됩니다.
     */
    private String currentPath = null;
    public void setCurrentPath(String targetPath) {
        this.currentPath = targetPath;
    }

    public String getCurrentPath() {
        return currentPath;
    }


    private FileServiceMonitor fileServiceManager = null;
    public FileServiceMonitor getFileServiceManager() {
        return fileServiceManager;
    }

    /**
     * {@link this#currentPath} 경로를 통해 탐색한 모든 파일를 담습니다.
     * 파일의 구체적인 상태는 {@link this#fileServiceManager}를 통해 정보를 읽어들일 수 있습니다.
     */
    private final List<File> registeredFile = new ArrayList<>();
    public List<File> getRegisteredFile() { return registeredFile; }

    /**
     * 특정한 문자열이 포함되어 있는 파일명을 모두 찾아 반환합니다.
     * @param containString 검색하고자 하는 문자열
     * @return 검색된 파일 리스트
     */
    public abstract List<File> search(String containString);

    /**
     * 파일 리스트를 파일로 저장합니다.
     * @return 리스트를 파일로 저장했는지에 대한 성공 여부
     */
    public Boolean saveToFileList(String saveFile) { return this.saveToFileList(new File(saveFile)); }

    /**
     * 파일 리스트를 파일로 저장합니다.
     * @return 리스트를 파일로 저장했는지에 대한 성공 여부
     */
    public Boolean saveToFileList(File saveFile)
    {
        try
        {
            FileWriter fileWriter = new FileWriter(saveFile);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            for(File file : this.registeredFile)
                bw.write(file.getAbsolutePath() + "\r\n");
            bw.close();
            return true;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 파일 리스트가 저장된 파일로부터 데이터를 가져옵니다.
     * @param listFile 리스트 정보가 담긴 파일 경로
     * @return 파일로부터 데이터를 로드했는가에 대한 성공 여부
     */
    public Boolean loadToFileList(String listFile) { return this.loadToFileList(new File(listFile)); }

    /**
     * 파일 리스트가 저장된 파일로부터 데이터를 가져옵니다.
     * @param listFile 리스트 정보가 담긴 파일
     * @return 파일로부터 데이터를 로드했는가에 대한 성공 여부
     */
    public Boolean loadToFileList(File listFile)
    {
        try
        {
            FileReader fileReader = new FileReader(listFile);
            BufferedReader br = new BufferedReader(fileReader);

            String line;
            while((line = br.readLine()) != null)
            {
                File path = new File(line);
                this.registeredFile.add(path);
            }

            br.close();
            return true;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * {@link this#currentPath} 디렉토리를 기준으로 하여 모든 하위 파일들은 탐색하고 등록합니다.<br>
     * 만약 파일 리스트가 존재한다면 {@link this#loadToFileList(File)}를 이용하여 리스트를 불러오는 것이 권장됩니다.
     */
    public void scan() {
        if(this.currentPath == null) {
            throw new NullPointerException("targetPath is null. Please configure this value");
        }
        File targetFile = new File(this.currentPath);
        if (targetFile.isFile())
            targetFile = new File(targetFile.getPath());

        this.scan0(targetFile);
    }

    /**
     * 실행하는 프로그램 경로를 기준으로 하여 모든 하위 파일들을 탐색하고 등록합니다.
     */
    public void scanCurrentDirectory() {
        this.currentPath = System.getProperty("user.dir");
        File targetFile = new File(this.currentPath);
        if(targetFile.isFile())
            targetFile = new File(targetFile.getPath());

        this.scan0(targetFile);
    }

    /**
     * 하위 디렉토리를 탐색하면서 파일 및 디렉토리를 등록합니다.
     * 해당 메소드는 일반적인 방법으로 호출할 수 없습니다.
     * @param target 하위 디렉토리
     */
    private synchronized void scan0(File target)
    {
        if(target.isFile())
        {
            this.registeredFile.add(target);
        }
        else
        {
            File[] listFiles = target.listFiles();
            if(Objects.requireNonNull(listFiles).length != 0)
            {
                for(File file : listFiles)
                {
                    if(file.isFile())
                        this.registeredFile.add(file);

                    else if(file.isDirectory())
                    {
                        this.scan0(file);
                    }
                }
            }
        }
    }
}
