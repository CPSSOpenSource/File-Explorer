package kr.ac.sch.whitehac97.program.service;

import java.nio.file.*;
import java.util.List;

public class FileServiceMonitor
{

    public FileServiceMonitor()
    {

    }

    void a() {
        while (true) {

            try {

                WatchService watchService = null;
                // 지정된 디렉토리에 변경이되는지 이벤트를 모니터링한다.

                WatchKey changeKey = watchService.take();



                List<WatchEvent<?>> watchEvents = changeKey.pollEvents();



                for (WatchEvent<?> watchEvent : watchEvents) {

                    // Ours are all Path type events:

                    WatchEvent<Path> pathEvent = (WatchEvent<Path>) watchEvent;



                    Path path = pathEvent.context();

                    WatchEvent.Kind<Path> eventKind = pathEvent.kind();



                    System.out.println(eventKind + " for path: " + path);

                }



                changeKey.reset(); // Important!



            } catch (InterruptedException e) {

                e.printStackTrace();

            }



        }
    }
}
