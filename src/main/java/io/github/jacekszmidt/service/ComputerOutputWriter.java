package io.github.jacekszmidt.service;

import io.github.jacekszmidt.model.Laptop;
import io.github.jacekszmidt.model.PersonalComputer;
import io.github.jacekszmidt.model.User;

public interface ComputerOutputWriter {
    void writeOutput(User user, UserService userService, Laptop laptop);

    void writeOutput(User user,UserService userService, PersonalComputer personalComputer);
}


