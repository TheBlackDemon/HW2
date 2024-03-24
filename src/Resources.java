public class Resources {
    private static boolean mute = false;
    private static Sound themeSong;
    private static Sound[] sounds = new Sound[1];
    public static void init(){
        initSounds();
    }

    public static void initSounds(){
        themeSong = new Sound("C:\\Users\\asgari\\Desktop\\untitled5\\src\\themeSong.wav" );
        sounds[0] = themeSong;
    }
    public static void stopSounds(){
        for (int i = 0; i < sounds.length; i++) {
            Sound sound = Resources.getSounds()[i];
            if(sound != null) {
                sound.stop();
            }
        }
    }

    public static Sound[] getSounds() {
        return sounds;
    }

    public static boolean isMute() {
        return mute;
    }

    public static void setMute(boolean mute) {
        Resources.mute = mute;
    }

    public static Sound getThemeSong() {
        return themeSong;
    }
}
