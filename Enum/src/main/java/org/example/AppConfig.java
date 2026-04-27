package org.example;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *Create an enum `AppConfig` Singleton with:
 *   - databaseUrl
 *   - maxConnections
 *   - timeout
 *   - debug mode
 *
 * 1. Serialize it to a file
 * 2. Deserialize it back
 * 3. Verify it's the SAME instance (== check)
 * 4. Modify a field via reflection (expect it to fail)
 * 5. Add a method resetToDefaults()
 *
 * Prove that after deserialization:
 *   deserializedConfig == AppConfig.INSTANCE → true ✅
 */
public enum AppConfig implements Serializable {

    INSTANCE;

    // Configuration fields
    private String databaseUrl = "jdbc:postgresql://localhost:5432/dev";
    private int maxConnections = 10;
    private int timeout = 3000;
    private boolean debugMode = false;

    AppConfig() {}
    // Getters and Setters
    public String getDatabaseUrl() { return databaseUrl; }
    public void setDatabaseUrl(String url) { this.databaseUrl = url; }

    public int getMaxConnections() { return maxConnections; }
    public void setMaxConnections(int max) { this.maxConnections = max; }

    // 5. Reset to defaults method
    public void resetToDefaults() {
        this.databaseUrl = "jdbc:postgresql://localhost:5432/dev";
        this.maxConnections = 10;
        this.timeout = 3000;
        this.debugMode = false;
    }

    @Override
    public String toString() {
        return String.format("AppConfig[url=%s, connections=%d]", databaseUrl, maxConnections);
    }
}

class Deserialize{

    public static void main(String[] args) {

        AppConfig my_app = AppConfig.INSTANCE;
        my_app.setMaxConnections(10);

        String file_name = "Config.ser";
        try{
            ObjectOutputStream op = new ObjectOutputStream(
                    new FileOutputStream(
                            file_name
                    )
            );
            op.writeObject(my_app);
            op.close();

            ObjectInputStream ip = new ObjectInputStream(
                    new FileInputStream(
                            file_name
                    )
            );

            AppConfig deserialised = (AppConfig) ip.readObject();

            ip.close();
            System.out.println("Hashcode of Original " +
                    op.hashCode());
            System.out.println("Hashcode of Deserialised " +
                    deserialised.hashCode());
            boolean isSame = (my_app == deserialised);
            System.out.println("deserializedConfig == AppConfig.INSTANCE → " + isSame + " ✅");

            // 4. Attempt to modify/instantiate via reflection
            System.out.println("\n--- Reflection Attack Test ---");
            try {
                Constructor<AppConfig> constructor = AppConfig.class.getDeclaredConstructor();
                constructor.setAccessible(true);
                constructor.newInstance();
            } catch (NoSuchMethodException e) {
                System.out.println("Failed as expected: Enums do not have a no-arg constructor.");
            } catch (IllegalArgumentException e) {
                // Java specifically blocks 'newInstance' for Enums at the language level
                System.out.println("Reflective instantiation blocked by JVM ✅");
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
