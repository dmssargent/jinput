package net.java.games.input;

class LinuxJoystickPOV extends LinuxJoystickAxis {

    private final LinuxJoystickAxis hatX;
    private final LinuxJoystickAxis hatY;


    LinuxJoystickPOV(Component.Identifier.Axis id, LinuxJoystickAxis hatX, LinuxJoystickAxis hatY) {
        super(id, false);
        this.hatX = hatX;
        this.hatY = hatY;
    }


    LinuxJoystickAxis getXAxis() {
        return hatX;
    }

    LinuxJoystickAxis getYAxis() {
        return hatY;
    }


    void updateValue() {
        float last_x = hatX.getPollData();
        float last_y = hatY.getPollData();

        resetHasPolled();
        if (last_x == -1 && last_y == -1)
            setValue(Component.POV.UP_LEFT);
        else if (last_x == -1 && last_y == 0)
            setValue(Component.POV.LEFT);
        else if (last_x == -1 && last_y == 1)
            setValue(Component.POV.DOWN_LEFT);
        else if (last_x == 0 && last_y == -1)
            setValue(Component.POV.UP);
        else if (last_x == 0 && last_y == 0)
            setValue(Component.POV.OFF);
        else if (last_x == 0 && last_y == 1)
            setValue(Component.POV.DOWN);
        else if (last_x == 1 && last_y == -1)
            setValue(Component.POV.UP_RIGHT);
        else if (last_x == 1 && last_y == 0)
            setValue(Component.POV.RIGHT);
        else if (last_x == 1 && last_y == 1)
            setValue(Component.POV.DOWN_RIGHT);
        else {
            LinuxEnvironmentPlugin.logln("Unknown values x = " + last_x + " | y = " + last_y);
            setValue(Component.POV.OFF);
        }
    }
}
