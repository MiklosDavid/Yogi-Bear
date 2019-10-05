package yogibear;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    
    private GameMode mode;
    private ArrayList<Sprite> sprites;
    private int ranger_num, tree_num, picnic_num;
    
    /**
     * A generátor konstruktora.
     * @param mode A játék nehézségi szintje.
     * @param yogiBear A játékos.
     */
    public Generator(GameMode mode, YogiBear yogiBear){
        this.mode = mode;
        sprites = new ArrayList<Sprite>();
        sprites.add(yogiBear);
    }
    
    /**
     * A konstrouktorban már megkapta a játék nehézségi szintjét. Ennek alapján előbb eldönti a különböző
     * Sprite-ok számát a játékmezőn. Előbb a Rangereket generálja, véletlenszerűen eldönti, hogy melyik irányba induljon.
     * Garantálva van, hogy YogiBear induló pozíciójában 300 pixeles körzetben nem lesz Ranger.
     * Ezt követően a fákat generáljuk. Mikor véletlenszerűen generálunk egy fát, ellenőrizzük, hogy ütközik-e más Sprite-okkal.
     * Ha igen, akkor ezt fa nem kerül bele a sprites tömbbe, hanem újrageneráljuk.
     * A piknik kosarak generálására ugyan ez jellemző.
     * @return Visszatér a legenerált Sprite-okkal.
     */
    public ArrayList<Sprite> generate(){
        
        Random rand = new Random();
        
        switch(mode){
            case EASY:
                ranger_num = 5;
                tree_num = 10;
                picnic_num = 6;
                break;
            case MEDIUM:
                ranger_num = 8;
                tree_num = 16;
                picnic_num = 8;
                break;
            case HARD:
                ranger_num = 12;
                tree_num = 24;
                picnic_num = 10;
                break;
            default:
                ranger_num = 0;
                tree_num = 0;
                picnic_num = 0;
        }
        
        for(int i = 0; i < ranger_num; i++){
            int x = rand.nextInt(GameEngine.getW_WIDTH() - 300 - Ranger.getRangerWidth()) + 300;
            int y = rand.nextInt(GameEngine.getW_HEIGHT() - 300 - Ranger.getRangerHeight()) + 300;
            
            Ranger ranger = new Ranger(x, y);
                int d = rand.nextInt(4);
                switch(d){
                    case 0:
                        ranger.setDirection(Direction.LEFT);
                        break;
                    case 1:
                        ranger.setDirection(Direction.RIGHT);
                        break;
                    case 2:
                        ranger.setDirection(Direction.UP);
                        break;
                    case 3:
                        ranger.setDirection(Direction.DOWN);
                        break;
                    default:
                        break;
                }
                sprites.add(ranger);
        }
        
        for(int i = 0; i < tree_num; i++){
            boolean badCoord = false;
            int x = rand.nextInt(GameEngine.getW_WIDTH() - Tree.getTreeWidth());
            int y = rand.nextInt(GameEngine.getW_HEIGHT() - Tree.getTreeHeight());
            
            Tree tree = new Tree(x, y);
            for(Sprite sprite : sprites){
                if(tree.collides(sprite)){
                    badCoord = true;
                    i--;
                    break;
                }
            }
            
            if(!badCoord){
                sprites.add(tree);
            }
            
        }
        
        for(int i = 0; i < picnic_num; i++){
            boolean badCoord = false;
            int x = rand.nextInt(GameEngine.getW_WIDTH() - Picnic.getPicnicWidth());
            int y = rand.nextInt(GameEngine.getW_HEIGHT() - Picnic.getPicnicHeight());
            
            Picnic picnic = new Picnic(x, y);
            for(Sprite sprite : sprites){
                if(picnic.collides(sprite)){
                    badCoord = true;
                    i--;
                    break;
                }
            }
            
            if(!badCoord){
                sprites.add(picnic);
            }
            
        }
        
        return sprites;
        
    }

    public int getPicnic_num() {
        return picnic_num;
    }
    
}
