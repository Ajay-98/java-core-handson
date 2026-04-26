package org.example;

import java.util.EnumMap;
import java.util.EnumSet;

public enum Permission {
    READ, WRITE, EXECUTE, DELETE, ADMIN;

    // ✅ EnumMap storing each Role's permissions — initialized once
    private static final EnumMap<Role, EnumSet<Permission>> ROLE_PERMISSIONS
            = new EnumMap<>(Role.class);

    // ✅ Static block — define all role permissions here
    static {
        ROLE_PERMISSIONS.put(Role.VIEWER,
                EnumSet.of(READ));

        ROLE_PERMISSIONS.put(Role.EDITOR,
                EnumSet.of(READ, WRITE));

        ROLE_PERMISSIONS.put(Role.MANAGER,
                EnumSet.of(READ, WRITE, DELETE));

        ROLE_PERMISSIONS.put(Role.ROOT,
                EnumSet.allOf(Permission.class));  // ALL permissions
    }

    // ✅ Method 1 — check if role has a specific permission
    public static boolean hasPermission(Role role, Permission permission) {
        return ROLE_PERMISSIONS
                .getOrDefault(role, EnumSet.noneOf(Permission.class))
                .contains(permission);
    }

    // ✅ Method 2 — get all permissions for a role
    public static EnumSet<Permission> getPermissions(Role role) {
        return EnumSet.copyOf(                         // return a COPY — never expose internal state
                ROLE_PERMISSIONS.getOrDefault(
                        role, EnumSet.noneOf(Permission.class)
                )
        );
    }

    // ✅ Method 3 — add a permission to a role dynamically
    public static EnumSet<Permission> addPermission(Role role, Permission permission) {
        ROLE_PERMISSIONS
                .getOrDefault(role, EnumSet.noneOf(Permission.class))
                .add(permission);
        return getPermissions(role);
    }

    // ✅ Method 4 — find permissions shared between two roles
    public static EnumSet<Permission> commonPermissions(Role r1, Role r2) {
        EnumSet<Permission> common = getPermissions(r1); // copy of r1's perms
        common.retainAll(getPermissions(r2));            // keep only what r2 also has
        return common;
    }
}
enum Role {
    VIEWER,
    EDITOR,
    MANAGER,
    ROOT
}
class TestPermissions {
    public static void main(String[] args) {

        // -- hasPermission --
        System.out.println("=== hasPermission ===");
        System.out.println("VIEWER  can READ?    "
                + Permission.hasPermission(Role.VIEWER,  Permission.READ));    // true
        System.out.println("VIEWER  can WRITE?   "
                + Permission.hasPermission(Role.VIEWER,  Permission.WRITE));   // false
        System.out.println("EDITOR  can WRITE?   "
                + Permission.hasPermission(Role.EDITOR,  Permission.WRITE));   // true
        System.out.println("MANAGER can DELETE?  "
                + Permission.hasPermission(Role.MANAGER, Permission.DELETE));  // true
        System.out.println("MANAGER can ADMIN?   "
                + Permission.hasPermission(Role.MANAGER, Permission.ADMIN));   // false
        System.out.println("ROOT    can ADMIN?   "
                + Permission.hasPermission(Role.ROOT,    Permission.ADMIN));   // true

        // -- getPermissions --
        System.out.println("\n=== getPermissions ===");
        for (Role role : Role.values()) {
            System.out.println(role + " → " + Permission.getPermissions(role));
        }

        // -- addPermission --
        System.out.println("\n=== addPermission ===");
        System.out.println("VIEWER before: " + Permission.getPermissions(Role.VIEWER));
        Permission.addPermission(Role.VIEWER, Permission.EXECUTE);
        System.out.println("VIEWER after:  " + Permission.getPermissions(Role.VIEWER));

        // -- commonPermissions --
        System.out.println("\n=== commonPermissions ===");
        System.out.println("EDITOR & MANAGER share: "
                + Permission.commonPermissions(Role.EDITOR, Role.MANAGER));   // [READ, WRITE]
        System.out.println("VIEWER & ROOT share:    "
                + Permission.commonPermissions(Role.VIEWER, Role.ROOT));      // [READ]
        System.out.println("VIEWER & EDITOR share:  "
                + Permission.commonPermissions(Role.VIEWER, Role.EDITOR));    // [READ]
    }
}