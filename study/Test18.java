enum UserRole {
    ADMIN("管理员", "拥有系统最高权限，可以管理所有用户和数据"),
    USER("普通用户", "可以访问基本功能，如查看和修改个人信息"),
    GUEST("访客", "只能浏览公开信息，不能进行修改操作");

    private final String roleName;
    private final String permissionDescription;

    UserRole(String roleName, String permissionDescription) {
        this.roleName = roleName;
        this.permissionDescription = permissionDescription;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void displayPermissions() {
        System.out.println("角色: " + roleName);
        System.out.println("权限说明: " + permissionDescription);
        System.out.println();
    }

    public boolean isAdmin() {
        return this == ADMIN;
    }
    public boolean isUser() {
        return this == USER;
    }
    public boolean isGuest() {
        return this == GUEST;
    }
}

class User {
    private String username;
    private UserRole role;

    public User(String username, UserRole role) {
        this.username = username;
        this.role = role;
    }
    public String getUsername() {
        return username;
    }
    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }
    public void displayUserInfo() {
        System.out.println("用户名: " + username);
        role.displayPermissions();
    }
}

public class Test {
    public static void main(String[] args) {
        User adminUser = new User("张三", UserRole.ADMIN);
        User normalUser = new User("李四", UserRole.USER);
        User guestUser = new User("王五", UserRole.GUEST);

        System.out.println("=== 用户权限管理系统 ===\n");
        adminUser.displayUserInfo();
        normalUser.displayUserInfo();
        guestUser.displayUserInfo();

        System.out.println("=== 直接显示权限信息 ===\n");
        for (UserRole role : UserRole.values()) {
            role.displayPermissions();
        }
        System.out.println("=== 权限判断示例 ===\n");
        UserRole userRole = UserRole.USER;
        if (userRole.isAdmin()) {
            System.out.println(userRole.getRoleName() + "具有管理员权限");
        } else {
            System.out.println(userRole.getRoleName() + "不具有管理员权限");
        }
    }
}