package javaProject.project.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javaProject.project.model.Utilisateur;

/**
 *
 * @author sosthene
 */
public final class CurentUserSingleton {

	private static CurentUserSingleton INSTANCE;
	private Utilisateur info;

	private CurentUserSingleton() {
	}

	public static CurentUserSingleton getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new CurentUserSingleton();
		}

		return INSTANCE;
	}

	// getters and setters

	public static CurentUserSingleton getINSTANCE() {
		return INSTANCE;
	}

	public Utilisateur getInfo() {
		return info;
	}

	public void setInfo(Utilisateur info) {
		this.info = info;
	}
}