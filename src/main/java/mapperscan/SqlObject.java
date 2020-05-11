package mapperscan;

import meng.springframework.container.Container;

public class SqlObject {

    private static Container sqlContainer = Container.INSTANTIATION;
    private static Container methodContainer = Container.INSTANTIATION;

    public static Container getSqlContainer(){
        return sqlContainer;
    }
    public static Container getMethodContainer(){
        return methodContainer;
    }
}
