package com.example.group12project.ChessComponents;

import androidx.annotation.IdRes;
import com.example.group12project.R;

public class Coordinates {

    private final int x;
    private final int y;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int get_x(){
        return x;
    }

    public int get_y(){
        return y;
    }

    public static Coordinates get_pos(@IdRes int n){
        Coordinates pos = null;
        switch(n){
            case(R.id.i00):{ pos = new Coordinates(0, 0);break;}
            case(R.id.i01):{ pos = new Coordinates(0, 1);break;}
            case(R.id.i02):{ pos = new Coordinates(0, 2);break;}
            case(R.id.i03):{ pos = new Coordinates(0, 3);break;}
            case(R.id.i04):{ pos = new Coordinates(0, 4);break;}
            case(R.id.i05):{ pos = new Coordinates(0, 5);break;}
            case(R.id.i06):{ pos = new Coordinates(0, 6);break;}
            case(R.id.i07):{ pos = new Coordinates(0, 7);break;}
            case(R.id.i10):{ pos = new Coordinates(1, 0);break;}
            case(R.id.i11):{ pos = new Coordinates(1, 1);break;}
            case(R.id.i12):{ pos = new Coordinates(1, 2);break;}
            case(R.id.i13):{ pos = new Coordinates(1, 3);break;}
            case(R.id.i14):{ pos = new Coordinates(1, 4);break;}
            case(R.id.i15):{ pos = new Coordinates(1, 5);break;}
            case(R.id.i16):{ pos = new Coordinates(1, 6);break;}
            case(R.id.i17):{ pos = new Coordinates(1, 7);break;}
            case(R.id.i20):{ pos = new Coordinates(2, 0);break;}
            case(R.id.i21):{ pos = new Coordinates(2, 1);break;}
            case(R.id.i22):{ pos = new Coordinates(2, 2);break;}
            case(R.id.i23):{ pos = new Coordinates(2, 3);break;}
            case(R.id.i24):{ pos = new Coordinates(2, 4);break;}
            case(R.id.i25):{ pos = new Coordinates(2, 5);break;}
            case(R.id.i26):{ pos = new Coordinates(2, 6);break;}
            case(R.id.i27):{ pos = new Coordinates(2, 7);break;}
            case(R.id.i30):{ pos = new Coordinates(3, 0);break;}
            case(R.id.i31):{ pos = new Coordinates(3, 1);break;}
            case(R.id.i32):{ pos = new Coordinates(3, 2);break;}
            case(R.id.i33):{ pos = new Coordinates(3, 3);break;}
            case(R.id.i34):{ pos = new Coordinates(3, 4);break;}
            case(R.id.i35):{ pos = new Coordinates(3, 5);break;}
            case(R.id.i36):{ pos = new Coordinates(3, 6);break;}
            case(R.id.i37):{ pos = new Coordinates(3, 7);break;}
            case(R.id.i40):{ pos = new Coordinates(4, 0);break;}
            case(R.id.i41):{ pos = new Coordinates(4, 1);break;}
            case(R.id.i42):{ pos = new Coordinates(4, 2);break;}
            case(R.id.i43):{ pos = new Coordinates(4, 3);break;}
            case(R.id.i44):{ pos = new Coordinates(4, 4);break;}
            case(R.id.i45):{ pos = new Coordinates(4, 5);break;}
            case(R.id.i46):{ pos = new Coordinates(4, 6);break;}
            case(R.id.i47):{ pos = new Coordinates(4, 7);break;}
            case(R.id.i50):{ pos = new Coordinates(5, 0);break;}
            case(R.id.i51):{ pos = new Coordinates(5, 1);break;}
            case(R.id.i52):{ pos = new Coordinates(5, 2);break;}
            case(R.id.i53):{ pos = new Coordinates(5, 3);break;}
            case(R.id.i54):{ pos = new Coordinates(5, 4);break;}
            case(R.id.i55):{ pos = new Coordinates(5, 5);break;}
            case(R.id.i56):{ pos = new Coordinates(5, 6);break;}
            case(R.id.i57):{ pos = new Coordinates(5, 7);break;}
            case(R.id.i60):{ pos = new Coordinates(6, 0);break;}
            case(R.id.i61):{ pos = new Coordinates(6, 1);break;}
            case(R.id.i62):{ pos = new Coordinates(6, 2);break;}
            case(R.id.i63):{ pos = new Coordinates(6, 3);break;}
            case(R.id.i64):{ pos = new Coordinates(6, 4);break;}
            case(R.id.i65):{ pos = new Coordinates(6, 5);break;}
            case(R.id.i66):{ pos = new Coordinates(6, 6);break;}
            case(R.id.i67):{ pos = new Coordinates(6, 7);break;}
            case(R.id.i70):{ pos = new Coordinates(7, 0);break;}
            case(R.id.i71):{ pos = new Coordinates(7, 1);break;}
            case(R.id.i72):{ pos = new Coordinates(7, 2);break;}
            case(R.id.i73):{ pos = new Coordinates(7, 3);break;}
            case(R.id.i74):{ pos = new Coordinates(7, 4);break;}
            case(R.id.i75):{ pos = new Coordinates(7, 5);break;}
            case(R.id.i76):{ pos = new Coordinates(7, 6);break;}
            case(R.id.i77):{ pos = new Coordinates(7, 7);break;}
            default:{
                break;
            }
        }

        return pos;
    }
}
