package com.daohen.personal.toolbox.library.util;


import android.app.ActivityManager;
import android.content.Context;

public class SystemUtil {

    /**
     * 是否在主进程中
     * @return
     */
    public static boolean inMainProcess() {
        String packageName = Contexts.get().getContext().getPackageName();
        String processName = getProcessName(Contexts.get().getContext());
        return packageName.equals(processName);
    }

    /**
     * 当前进程的名字
     * @param context
     * @return
     */
    public static final String getProcessName(Context context) {
        String processName = null;

        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));

        while (true) {
            for (ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses()) {
                if (info.pid == android.os.Process.myPid()) {
                    processName = info.processName;
                    break;
                }
            }

            // go home
            if (!Strings.isNull(processName)) {
                return processName;
            }

            // take a rest and again
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
