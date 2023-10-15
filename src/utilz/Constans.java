package utilz;

import main.Game;
//Clase para guardar variables para que el codigo sea mas explicito al momento de leerlo
public class Constans {



    public static class EnemyConstans {
        public static final int CRABBY =0;

        public static final int IDLE =0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;


        public static final int CRABBY_WIDTH_DEFAULT = 72;
        public static final int CRABBY_HEIGHT_DEFAULT = 32;

        public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * Game.SCALE);
        public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * Game.SCALE);

        public static final int CRABBY_DRAWOFFSET_X = (int) ( 26*Game.SCALE);
        public static final int CRABBY_DRAWOFFSET_y = (int) ( 9*Game.SCALE);


        public static int GetSpriteAmount(int enemy_type,int enemy_state) {
            switch (enemy_type) {
                case CRABBY:
                    switch (enemy_state) {
                        case IDLE:
                            return 9;
                        case RUNNING:
                            return 6;
                        case ATTACK:
                            return 7;
                        case HIT:
                            return 4;
                        case DEAD:
                            return 5;
                    }
            }
            return 0;
        }
        public static int GetMaxHealth(int enemy_type){
            switch (enemy_type){
                case CRABBY:
                    return 10;
                default:
                    return 1;
            }
        }
        public static int GetEnemyDmg(int enemy_type){
            switch (enemy_type){
                case CRABBY:
                    return 10;
                default:
                    return 1;
            }
        }

    }

    public static class Enviroment{
        public static class Clouds{
            public static final int BIG_CLOUD_WIDTH_DEFAULT = 448;
            public static final int BIG_CLOUD_HEIGHT_DEFAULT = 101;

            public static final int BIG_CLOUD_WIDTH = (int) (BIG_CLOUD_WIDTH_DEFAULT * Game.SCALE);
            public static final int BIG_CLOUD_HEIGHT = (int) (BIG_CLOUD_HEIGHT_DEFAULT * Game.SCALE);

            public static final int SMALL_CLOUD_WIDTH_DEFAULT = 74;
            public static final int SMALL_CLOUD_HEIGHT_DEFAULT = 24;

            public static final int SMALL_CLOUD_WIDTH = (int) (SMALL_CLOUD_WIDTH_DEFAULT * Game.SCALE);
            public static final int SMALL_CLOUD_HEIGHT = (int) (SMALL_CLOUD_HEIGHT_DEFAULT * Game.SCALE);

        }
    }


    public static class UI{
        public static class Buttons{
            public static final int B_WIDTH_DEFAULT = 128;
            public static final int B_HEIGHT_DEFAULT = 46;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
        }
        public static class PauseButtons{
            public static final int SOUND_SIZE_DEFAULT = 30;
            public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
        }
        public static class URMButtons{
            public static final int URM_DEFAULT_SIZE = 30;
            public static final int URM_SIZE= (int) (URM_DEFAULT_SIZE * Game.SCALE);
        }
    }

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants{

        public static  final int ATTACK = 0;
        public static  final int FALLING = 1;
        public static  final int RUNNING = 2;
        public static  final int JUMP = 3;
        public static  final int IDLE = 4;

        public static int GetSpriteAmount(int player_action){
            switch (player_action){
                case RUNNING:
                    return 6;
                case IDLE:
                    return 12;
                case ATTACK:
                    return 5;
                case JUMP:
                    return 3;
                case FALLING:
                    return 2;
                default:
                    return 1;
            }

        }



    }

}
