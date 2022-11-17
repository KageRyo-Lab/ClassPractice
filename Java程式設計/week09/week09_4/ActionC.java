package week09.week09_4;

import static java.lang.System.out;

public enum ActionC implements Command{
    RIGHT{
        public void execute() {
            out.println("播放右轉動畫");
        }
    }
}
