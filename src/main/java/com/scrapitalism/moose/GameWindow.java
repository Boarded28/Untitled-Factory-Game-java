package com.scrapitalism.moose;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_MAXIMIZED;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import static org.lwjgl.system.MemoryUtil.NULL;

public class GameWindow {
    
    private int _width, _height;
    private String _title, _gameVersion;
    private long _winHandle;
    private static GameWindow _window = null;

    private GameWindow() {
        this._width = 800;
        this._height = 600;
        this._title = "Untitled Factory Game";
    }

    public static GameWindow getInstance() {
        if (GameWindow._window == null) {
            GameWindow._window = new GameWindow();
        }
        return GameWindow._window;
    }

    public void setGameVersion(String version) {
        this._gameVersion = version == null ? "1.0-Dev" : version;
    }

    public void run() {
        System.out.println(_title + " version " + _gameVersion);
        System.out.println("Using LWJGL version " + Version.getVersion());

        init();
        loop();
    }

    private void init() {
        // Setup error callback to print errors in the console
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initalize GLFW");
        }

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Create the window
        _winHandle = glfwCreateWindow(this._width, this._height, (this._title + " v" + this._gameVersion), NULL, NULL);
        if (_winHandle == 0) {
            throw new IllegalStateException("Failed to create the GLFW window");
        }

        // Make OpenGL context current
        glfwMakeContextCurrent(_winHandle);

        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(_winHandle);

        // Create capabilities, make OpenGL bindings available for use.
        GL.createCapabilities();
    }

    private void loop() {
        while (!glfwWindowShouldClose(_winHandle)) {
            // Poll events
            glfwPollEvents();

            glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(_winHandle);
        }
    }
}
