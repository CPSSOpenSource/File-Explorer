package kr.ac.sch.whitehac97.program;

import kr.ac.sch.whitehac97.program.explorer.ExplorerBase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileExplorer extends ExplorerBase
{
    public FileExplorer()
    {
        super();
    }

    public FileExplorer(String currentPath)
    {
        super(currentPath);
    }

    @Override
    public List<File> search(String containString)
    {
        ArrayList<File> result = new ArrayList<>();
        if(this.getRegisteredFile().size() == 0) return null;
        for(File currentFileType : this.getRegisteredFile())
        {
            if(currentFileType.getName().contains(containString))
            {
                result.add(currentFileType);
            }
        }
        return result;
    }
}
