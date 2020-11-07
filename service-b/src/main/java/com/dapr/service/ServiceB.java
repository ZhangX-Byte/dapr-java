package com.dapr.service;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

/**
 * @author Zhang_Xiang
 * @since 2020/11/7 10:51:22
 */
public class ServiceB {

    /**
     * Starts the service.
     *
     * @param args Expects the port: -p PORT
     * @throws Exception If cannot start service.
     */
    public static void main(String[] args) throws Exception {
        Options options = new Options();
        options.addRequiredOption("p", "port", true, "Port to listen to.");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        // If port string is not valid, it will throw an exception.
        int port = Integer.parseInt(cmd.getOptionValue("port"));

        DaprApplication.start(port);
    }
}
