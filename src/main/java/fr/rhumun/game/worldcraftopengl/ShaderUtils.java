package fr.rhumun.game.worldcraftopengl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static fr.rhumun.game.worldcraftopengl.Game.SHADERS_PATH;
import static org.lwjgl.opengl.GL20.*;

public class ShaderUtils {

    public static int loadShader(String vertexPath, String fragmentPath) throws IOException {
        String vertexCode = new String(Files.readAllBytes(Paths.get(SHADERS_PATH + vertexPath)));
        String fragmentCode = new String(Files.readAllBytes(Paths.get(SHADERS_PATH + fragmentPath)));

        int vertexShader = compileShader(vertexCode, GL_VERTEX_SHADER);
        int fragmentShader = compileShader(fragmentCode, GL_FRAGMENT_SHADER);

        // Créer un programme shader
        int shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragmentShader);
        glLinkProgram(shaderProgram);

        // Vérifier les erreurs de linkage
        if (glGetProgrami(shaderProgram, GL_LINK_STATUS) == GL_FALSE) {
            System.err.println("Erreur lors du linkage des shaders.");
            System.err.println(glGetProgramInfoLog(shaderProgram));
            System.exit(-1);
        }

        // Nettoyer
        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);

        return shaderProgram;
    }

    private static int compileShader(String code, int type) {
        int shader = glCreateShader(type);
        glShaderSource(shader, code);
        glCompileShader(shader);

        if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Erreur de compilation du shader.");
            System.err.println(glGetShaderInfoLog(shader));
            System.exit(-1);
        }

        return shader;
    }
}
