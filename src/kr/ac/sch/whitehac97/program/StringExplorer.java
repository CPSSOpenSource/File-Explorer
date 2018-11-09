package kr.ac.sch.whitehac97.program;

import kr.ac.sch.whitehac97.program.explorer.ExplorerBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringExplorer extends ExplorerBase
{
    public StringExplorer() { }

    public StringExplorer(String currentPath)
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
            try {
                BufferedReader reader = new BufferedReader(new FileReader(currentFileType));
                String s;
                int line = 1;
                while((s = reader.readLine()) != null)
                {
                    if(s.contains(containString))
                    {
                        result.add(currentFileType);
                        System.out.println(String.format("Found: %s in line %d at %d", currentFileType.getAbsolutePath(), line, s.indexOf(containString)));
                        reader.close();
                        break;
                    }
                    line++;
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }
}
