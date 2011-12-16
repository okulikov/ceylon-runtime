package ceylon.modules;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ceylon.modules.spi.Argument;
import ceylon.modules.spi.ArgumentType;

/**
 * Holds the configuration from the command-line arguments
 * 
 * @author Stephane Epardaud <stef@epardaud.fr>
 */
public class Configuration {

    /**
     * Ceylon module to run
     */
    public String module;

    /**
     * Ceylon program arguments
     */
    public String[] arguments;

    /**
     * List of module repositories
     */
    public List<String> repositories = new LinkedList<String>();

    /**
     * Name of class or toplevel method to run
     */
    public String run;

    /**
     * Source path
     */
    public String src;

    // Non-Ceylon
    public String executable;
    public boolean cacheContent;
    /** names of implementation classes by interface name */
    public Map<String,String> impl = new HashMap<String,String>();

    /**
     * Sets an argument and checks its required number of parameters
     * @param name argument name without the prefix -/+
     * @param type type of option
     * @param values list of arguments
     * @param i index of the argument name
     * @return the index of the last argument required eaten by this argument
     */
    public int setArgument(String name, ArgumentType type, String[] values, int i) {
        Argument argument = Argument.forArgumentName(name, type);
        if(argument == null)
            throw new IllegalArgumentException("Unknown argument: "+name);

        if (i + argument.getRequiredValues() >= values.length)
            throw new IllegalArgumentException("Missing argument value: " + name);

        int arg = i+1;
        switch(argument){
        case EXECUTABLE: executable = values[arg]; break;
        case CACHE_CONTENT: cacheContent = true; break;
        case IMPLEMENTATION: impl.put(values[arg], values[arg+1]); break;
        case SOURCE: src = values[arg]; break;
        case RUN: run = values[arg]; break;
        case REPOSITORY: repositories.add(values[arg]); break;
        }

        return i+argument.getRequiredValues();
    }
}