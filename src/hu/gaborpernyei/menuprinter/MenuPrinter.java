package hu.gaborpernyei.menuprinter;

public class MenuPrinter {

    public static void main(String[] args) {
        MenuItem rootMenu = new MenuItem( "File");
        MenuItem newMenu = new MenuItem("New");

        newMenu.setSubItems( new MenuItem("Project"), new MenuItem("Project from existing sources"),
                             new MenuItem( "Project from Version Control")
                );
        rootMenu.setSubItems( newMenu, new MenuItem("Open") , new MenuItem( "Open URL"),
                new MenuItem( "Open Recent"), new MenuItem("Open Project")
                );

        System.out.println(rootMenu.getFullName(0,0));
    }

}

class MenuItem{
    private String name;
    private MenuItem[] subItems;

    public String getFullName(Integer depth, int itemCount){
        String retVal;
        String prePos;
        char c;

        switch( depth ) {
            case 0: prePos = "- ";
                break;
            case 1: c = (char) ((char)itemCount + 'a');
                prePos = c+"./ ";
                break;
            case 2: prePos = "* ";
                break;
            default: prePos = "";
        }
        retVal = prePos + getName();
        if( subItems != null) {
            for( int forIdx = 0; forIdx < subItems.length; forIdx ++) {
                MenuItem mi = subItems[ forIdx ];
                retVal = retVal + System.getProperty("line.separator") + mi.getFullName( depth +1, forIdx);
            }
        }
        return retVal;
    }

    public MenuItem( ){}

    public MenuItem(String name){
        this.name = name;
        this.subItems = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubItems(MenuItem... subItems) {
        this.subItems = subItems;
    }

    public String getName() {
        return name;
    }

    public MenuItem[] getSubItems() {
        return subItems;
    }
}
