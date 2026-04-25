package org.example;

public enum LogLevel {
    DEBUG("DEBUG", 1 ),
    INFO("INFO", 0 ),
    WARNING("WARNING", 2 ),
    ERROR("ERROR", 3);

    private final String Severity;
    private final int level;

    LogLevel(String severity, int level) {

        this.Severity = severity;
        this.level = level;
    }
    public boolean isVerbose(){
        if(!(WARNING.level >= this.level)) {
            return true;
        }
        return false;
    }
    public String Severity(){
        return Severity;
    }

    public static void main(String[] args) {

        System.out.println("Testing Log Levels");
        LogLevel logLevel1 = LogLevel.INFO;
        System.out.printf("Log Level is %d | Severity is %s | Verbose? %b",
                logLevel1.level, logLevel1.Severity, logLevel1.isVerbose());


    }

}


