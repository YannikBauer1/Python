package mpjp.client;


import java.io.IOException;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import mpjp.shared.MPJPException;
import mpjp.shared.PuzzleInfo;


public class Webapps implements EntryPoint {
	
	/*--------------------------------------------------------------------*/
	/*------------------------ Variables ---------------------------------*/
	/*--------------------------------------------------------------------*/
	
	/**
	 *  A remote service proxy to talk to the server-side Manager 
	 */
	public final ManagerServiceAsync managerService = 
			GWT.create(ManagerService.class);
	
	/**
	 *  Username of the client
	 */
	private String username = new String();
	
	/**
	 *  WorkspaceId of workspace on whitch is beeing played
	 */
	private static String workspaceId = new String();
	
	
	/*----------------------------------------------------------------------*/
	/*------------------------ Functions -----------------------------------*/
	/*----------------------------------------------------------------------*/
	
	/**
	 *  Function to write on the web console
	 */
	public static native void console(String text)
	/*-{
	    console.log(text);
	}-*/;
	
	/**
	 *  This is the entry point method.
	 */
	public void onModuleLoad() {
		createLoginMenu();
		createMainMenu();
		RootPanel.get("mainMenu").getElement().getStyle()
			.setDisplay(Display.NONE);
		createCreateGameMenu();
		RootPanel.get("createGameMenu").getElement().getStyle()
			.setDisplay(Display.NONE);
		createJoinGameMenu();
		RootPanel.get("joinGameMenu").getElement().getStyle()
			.setDisplay(Display.NONE);
		createPuzzleMenu();
		RootPanel.get("puzzleMenu").getElement().getStyle()
			.setDisplay(Display.NONE);
	}
	
	
	/*-------------------------------------------------------------------------*/
	/*------------------- Create Hmtl Functions -------------------------------*/
	/*-------------------------------------------------------------------------*/
	
	/**
	 *  Creates the Login Menu
	 */
	public void createLoginMenu() {
		RootPanel.get("loginTitle").getElement().getStyle()
			.setFontSize(12, Unit.EM);
		RootPanel.get("username").getElement().setInnerText("Username:");
		RootPanel.get("username").getElement().getStyle()
			.setFontSize(25, Unit.PX);;
		final TextBox usernameTextBox = new TextBox();
		usernameTextBox.getElement().getStyle().setFontSize(20, Unit.PX);
		final Button loginButton = new Button("Login");
		loginButton.getElement().getStyle().setWidth(350, Unit.PX);
		loginButton.getElement().getStyle().setFontSize(20, Unit.PX);
		
		RootPanel.get("usernameField").add(usernameTextBox);
		RootPanel.get("loginButtonField").add(loginButton);
		
		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(usernameTextBox.getText()!="") {
					username=usernameTextBox.getText();
					usernameTextBox.setText("");
					RootPanel.get("mainMenu").getElement().getStyle()
						.setDisplay(Display.BLOCK);
					RootPanel.get("loginMenu").getElement().getStyle()
						.setDisplay(Display.NONE);
					RootPanel.get("loginErro").getElement().setInnerText("");
				} else {
					RootPanel.get("loginErro").getElement()
						.setInnerText("(Didnt write a username)");
				}
			}
		});
	}
	
	/**
	 *  Creates the Main Menu
	 */
	public void createMainMenu() {
		RootPanel.get("mainTitle").getElement().getStyle()
			.setFontSize(12, Unit.EM);
		final Button createGameButton = new Button("Create Game");
		createGameButton.getElement().getStyle().setWidth(350, Unit.PX);
		createGameButton.getElement().getStyle().setFontSize(20, Unit.PX);
		final Button joinGameButton = new Button("Join Game");
		joinGameButton.getElement().getStyle().setWidth(350, Unit.PX);
		joinGameButton.getElement().getStyle().setFontSize(20, Unit.PX);
		final Button logoutButton = new Button("Logout");
		logoutButton.getElement().getStyle().setWidth(350, Unit.PX);
		logoutButton.getElement().getStyle().setFontSize(20, Unit.PX);

		RootPanel.get("mainMenu").add(createGameButton);
		RootPanel.get("mainMenu").add(new HTML("<br>"));
		RootPanel.get("mainMenu").add(joinGameButton);
		RootPanel.get("mainMenu").add(new HTML("<br>"));
		RootPanel.get("mainMenu").add(logoutButton);
		
		createGameButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                RootPanel.get("mainMenu").getElement().getStyle()
                	.setDisplay(Display.NONE);
                RootPanel.get("createGameMenu").getElement().getStyle()
                	.setDisplay(Display.BLOCK);
            }
        });
        joinGameButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                RootPanel.get("mainMenu").getElement().getStyle()
                	.setDisplay(Display.NONE);
                RootPanel.get("joinGameMenu").getElement().getStyle()
                	.setDisplay(Display.BLOCK);
            }
        });
        logoutButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	username=new String();
                RootPanel.get("mainMenu").getElement().getStyle()
                	.setDisplay(Display.NONE);
                RootPanel.get("loginMenu").getElement().getStyle()
                	.setDisplay(Display.BLOCK);
            }
        });
	}
	
	/**
	 *  Creates the Create Game Menu
	 */
	public void createCreateGameMenu() {
		RootPanel.get("gameName").getElement().setInnerText("Game name:");
		RootPanel.get("gameName").getElement().getStyle()
			.setFontSize(20, Unit.PX);
		final TextBox gameNameTextBox = new TextBox();
		gameNameTextBox.getElement().getStyle().setFontSize(15, Unit.PX);
		RootPanel.get("gamePassword").getElement().setInnerText("Password:");
		RootPanel.get("gamePassword").getElement().getStyle()
			.setFontSize(20, Unit.PX);
		final TextBox gamePasswordTextBox = new TextBox();
		gamePasswordTextBox.getElement().getStyle().setFontSize(15, Unit.PX);
		RootPanel.get("image").getElement().setInnerText("Select Image:");
		RootPanel.get("image").getElement().getStyle()
			.setFontSize(20, Unit.PX);
		final ListBox imageListBox = new ListBox();
		imageListBox.getElement().getStyle().setFontSize(20, Unit.PX);
		imageListBox.getElement().getStyle().setWidth(172, Unit.PX);
		imageListBox.addItem("");
		try {
			managerService.getAvailableImages(
					new AsyncCallback<java.util.Set<java.lang.String>>() {
				public void onFailure(Throwable caught) {
					console("Failed getAvailableImages:"+caught.getMessage());
				}
				public void onSuccess(java.util.Set<java.lang.String> result) {
					for (String i:result) {
						imageListBox.addItem(i);
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		RootPanel.get("cuttingDimension").getElement()
			.setInnerText("Select cutting dimension: ");
		RootPanel.get("cuttingDimension").getElement().getStyle()
			.setFontSize(20, Unit.PX);
		final ListBox cuttingDimensionListBox = new ListBox();
		cuttingDimensionListBox.getElement().getStyle()
			.setFontSize(20, Unit.PX);
		cuttingDimensionListBox.getElement().getStyle().setWidth(172, Unit.PX);
		cuttingDimensionListBox.addItem("");
		cuttingDimensionListBox.addItem("3x3");
		cuttingDimensionListBox.addItem("5x5");
		cuttingDimensionListBox.addItem("7x7");
		cuttingDimensionListBox.addItem("9x9");
		cuttingDimensionListBox.addItem("11x11");
		RootPanel.get("cuttingShape").getElement()
			.setInnerText("Select cutting shape:");
		RootPanel.get("cuttingShape").getElement().getStyle()
			.setFontSize(20, Unit.PX);
		final ListBox cuttingShapeListBox = new ListBox();
		cuttingShapeListBox.getElement().getStyle().setFontSize(20, Unit.PX);
		cuttingShapeListBox.getElement().getStyle().setWidth(172, Unit.PX);
		cuttingShapeListBox.addItem("");
		managerService.getAvailableCuttings(
				new AsyncCallback<java.util.Set<java.lang.String>>() {
			public void onFailure(Throwable caught) {
				console("Failed getAvailableCuttings:"+caught.getMessage());
			}
			public void onSuccess(java.util.Set<java.lang.String> result) {
				for (String i:result) {
					cuttingShapeListBox.addItem(i);
				}
			}
		});
		final Button createPuzzleButton = new Button("Create Puzzle");
		createPuzzleButton.getElement().getStyle().setWidth(400, Unit.PX);
		createPuzzleButton.getElement().getStyle().setFontSize(20, Unit.PX);
		final Button back1Button = new Button("Back");
		back1Button.getElement().getStyle().setFontSize(20, Unit.PX);
		back1Button.getElement().getStyle().setMargin(2, Unit.PX);
		
		RootPanel.get("back1").add(back1Button);
		RootPanel.get("gameNameField").add(gameNameTextBox);
		RootPanel.get("gamePasswordField").add(gamePasswordTextBox);
		RootPanel.get("imageField").add(imageListBox);
		RootPanel.get("cuttingDimensionField").add(cuttingDimensionListBox);
		RootPanel.get("cuttingShapeField").add(cuttingShapeListBox);
		RootPanel.get("createGameMenu").add(new HTML("<br>"));
		RootPanel.get("createGameMenu").add(createPuzzleButton);
		
		back1Button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	
            	RootPanel.get("createErro").getElement().setInnerText("");
            	gameNameTextBox.setText("");
            	gamePasswordTextBox.setText("");
            	imageListBox.setItemSelected(0, true);
            	cuttingDimensionListBox.setItemSelected(0, true);
            	cuttingShapeListBox.setItemSelected(0, true);
                RootPanel.get("createGameMenu").getElement().getStyle()
                	.setDisplay(Display.NONE);
                RootPanel.get("mainMenu").getElement().getStyle()
                	.setDisplay(Display.BLOCK);
            }
        });
		createPuzzleButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	String gameName=gameNameTextBox.getText();
            	String gamePassword=gamePasswordTextBox.getText();
            	String image=imageListBox.getSelectedItemText();
            	String[] cuttingDimension=cuttingDimensionListBox
            			.getSelectedItemText().split("x");
            	String cuttingShape=cuttingShapeListBox.getSelectedItemText();
            	if(gameName!="" && gamePassword!="" && image!="" && 
            			cuttingDimension[0]!="" && cuttingShape!="") {
            		RootPanel.get("createErro").getElement().setInnerText("");
	            	int rows = Integer.parseInt(cuttingDimension[0]);
	            	int columns = Integer.parseInt(cuttingDimension[1]);
	            	managerService.getImageWidhtHeight(image, 
	            				new AsyncCallback<java.util.List<Double>>() {
							public void onFailure(Throwable caught) {
								console("Fail getImageWidhtHeight:"+
										caught.getMessage());
							}
							public void onSuccess(java.util.List<Double> set) {
				            	double width = set.get(0);
				            	double height = set.get(1);
				            	PuzzleInfo puzzleInfo = new PuzzleInfo(image,
				            			cuttingShape,rows,columns,width,height);
				            	try {
									managerService.createWorkspace(puzzleInfo, 
											gameName,gamePassword, 
											new AsyncCallback<String>() {
										public void onFailure(Throwable caught) {
											console("Fail createWorkspace:"+
													caught.getMessage());
										}
										public void onSuccess(String id) {
											workspaceId=id;
											((Solver) RootPanel.get("puzzleSpace")
													.getWidget(0))
													.drawPuzzle(workspaceId);
										}
									});
								} catch (MPJPException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								}
				            	//managerService.addClient(username, workspaceId, );
							}
					});
	            	gameNameTextBox.setText("");
	            	gamePasswordTextBox.setText("");
	            	imageListBox.setItemSelected(0, true);
	            	cuttingDimensionListBox.setItemSelected(0, true);
	            	cuttingShapeListBox.setItemSelected(0, true);
	                RootPanel.get("createGameMenu").getElement().getStyle()
	                	.setDisplay(Display.NONE);
	                RootPanel.get("puzzleMenu").getElement().getStyle()
	                	.setDisplay(Display.BLOCK);
            	} else {
            		RootPanel.get("createErro").getElement()
            			.setInnerText("(Didnt fill in all information)");
            		RootPanel.get("createErro").getElement()
        				.getStyle().setColor("red");
            	}
            }
        });
	}
	
	/**
	 *  Creates the Join Game Menu
	 */
	public void createJoinGameMenu() {
		RootPanel.get("joinGameName").getElement().setInnerText("Game name:");
		RootPanel.get("joinGameName").getElement().getStyle()
			.setFontSize(20, Unit.PX);
		final TextBox joinGameNameTextBox = new TextBox();
		joinGameNameTextBox.getElement().getStyle()
			.setFontSize(15, Unit.PX);
		RootPanel.get("joinGamePassword").getElement()
			.setInnerText("Password:");
		RootPanel.get("joinGamePassword").getElement().getStyle()
			.setFontSize(20, Unit.PX);
		final TextBox joinGamePasswordTextBox = new TextBox();
		joinGamePasswordTextBox.getElement().getStyle()
			.setFontSize(15, Unit.PX);
		final Button back2Button = new Button("Back");
		back2Button.getElement().getStyle().setFontSize(20, Unit.PX);
		back2Button.getElement().getStyle().setMargin(2, Unit.PX);
		final Button joinGameButton = new Button("Join game");
		joinGameButton.getElement().getStyle().setWidth(290, Unit.PX);
		joinGameButton.getElement().getStyle().setFontSize(20, Unit.PX);
		
		RootPanel.get("back2").add(back2Button);
		RootPanel.get("joinGameNameField").add(joinGameNameTextBox);
		RootPanel.get("joinGamePasswordField").add(joinGamePasswordTextBox);
		RootPanel.get("joinGameMenu").add(new HTML("<br>"));
		RootPanel.get("joinGameMenu").add(joinGameButton);
		
		back2Button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	RootPanel.get("loginErro2").getElement().setInnerText("");
				joinGameNameTextBox.setText("");
				joinGamePasswordTextBox.setText("");
                RootPanel.get("joinGameMenu").getElement().getStyle()
                	.setDisplay(Display.NONE);
                RootPanel.get("mainMenu").getElement().getStyle()
                	.setDisplay(Display.BLOCK);
            }
        });
		joinGameButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	String gameName = joinGameNameTextBox.getText();
            	String password = joinGamePasswordTextBox.getText();
            	managerService.getWorkspaceIdFromName(gameName, password,
            			new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						console("Fail getWorkspaceIdFromName:"+
								caught.getMessage());
					}
					public void onSuccess(String result) {
						if (result!="") {
							RootPanel.get("loginErro2").getElement()
								.setInnerText("");
							joinGameNameTextBox.setText("");
							joinGamePasswordTextBox.setText("");
							workspaceId=result;
							((Solver) RootPanel.get("puzzleSpace").getWidget(0))
								.drawPuzzle(workspaceId);
							//managerService.addClient(username, workspaceId, );
							RootPanel.get("joinGameMenu").getElement().getStyle()
								.setDisplay(Display.NONE);
			                RootPanel.get("puzzleMenu").getElement().getStyle()
			                	.setDisplay(Display.BLOCK);
						} else {
							RootPanel.get("loginErro2").getElement()
								.setInnerText("Workspace or Password are wrong!");
							RootPanel.get("loginErro2").getElement()
								.getStyle().setColor("red");
						}
					}
				});
            }
        });
	}
	
	/**
	 *  Creates the Puzzle Menu
	 */
	public void createPuzzleMenu() {
		final Button exitButton = new Button("Exit");
		exitButton.getElement().getStyle().setFontSize(20, Unit.PX);
		exitButton.getElement().getStyle().setMargin(2, Unit.PX);
		final Button settingsButton = new Button("Settings");
		settingsButton.getElement().getStyle().setFontSize(20, Unit.PX);
		settingsButton.getElement().getStyle().setMargin(2, Unit.PX);
		
		RootPanel.get("settingsImage").getElement()
			.setInnerText("Change image: ");
		RootPanel.get("settingsImage").getElement().getStyle().
			setFontSize(20, Unit.PX);
		final ListBox imageListBox = new ListBox();
		imageListBox.getElement().getStyle().setFontSize(20, Unit.PX);
		imageListBox.getElement().getStyle().setWidth(172, Unit.PX);
		imageListBox.addItem("");
		try {
			managerService.getAvailableImages(new AsyncCallback<java.util.Set<java.lang.String>>() {
				public void onFailure(Throwable caught) {
					console("Fail getAvailableImages:"+caught.getMessage());
				}
				public void onSuccess(java.util.Set<java.lang.String> result) {
					for (String i:result) {
						imageListBox.addItem(i);
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		RootPanel.get("settingsCuttingDimension").getElement()
			.setInnerText("Change cutting dimension: ");
		RootPanel.get("settingsCuttingDimension").getElement().getStyle()
			.setFontSize(20, Unit.PX);
		final ListBox cuttingDimensionListBox = new ListBox();
		cuttingDimensionListBox.getElement().getStyle()
			.setFontSize(20, Unit.PX);
		cuttingDimensionListBox.getElement().getStyle()
			.setWidth(172, Unit.PX);
		cuttingDimensionListBox.addItem("");
		cuttingDimensionListBox.addItem("3x3");
		cuttingDimensionListBox.addItem("5x5");
		cuttingDimensionListBox.addItem("7x7");
		cuttingDimensionListBox.addItem("9x9");
		cuttingDimensionListBox.addItem("11x11");
		RootPanel.get("settingsCuttingShape").getElement()
			.setInnerText("Change cutting shape:");
		RootPanel.get("settingsCuttingShape").getElement().getStyle()
			.setFontSize(20, Unit.PX);
		final ListBox cuttingShapeListBox = new ListBox();
		cuttingShapeListBox.getElement().getStyle().setFontSize(20, Unit.PX);
		cuttingShapeListBox.getElement().getStyle().setWidth(172, Unit.PX);
		cuttingShapeListBox.addItem("");
		managerService.getAvailableCuttings(new AsyncCallback<java.util.Set<java.lang.String>>() {
			public void onFailure(Throwable caught) {
				console("Fail getAvailableCuttings:"+caught.getMessage());
			}
			public void onSuccess(java.util.Set<java.lang.String> result) {
				for (String i:result) {
					cuttingShapeListBox.addItem(i);
				}
			}
		});
		final Button changePuzzleButton = new Button("Change Puzzle");
		changePuzzleButton.getElement().getStyle().setWidth(400, Unit.PX);
		changePuzzleButton.getElement().getStyle().setFontSize(20, Unit.PX);
		final Button exitSettingsButton = new Button("X");
		exitSettingsButton.getElement().getStyle().setFontSize(20, Unit.PX);
		exitSettingsButton.getElement().getStyle().setMargin(2, Unit.PX);
		
		RootPanel.get("exit").add(exitButton);
		RootPanel.get("settings").add(settingsButton);	
		
		RootPanel.get("exitSettings").add(exitSettingsButton);
		RootPanel.get("settingsImageField").add(imageListBox);
		RootPanel.get("settingsCuttingDimensionField")
			.add(cuttingDimensionListBox);
		RootPanel.get("settingsCuttingShapeField").add(cuttingShapeListBox);
		RootPanel.get("settingsSpace").add(new HTML("<br>"));
		RootPanel.get("settingsSpace").add(changePuzzleButton);
		
		Solver s = new Solver();
		RootPanel.get("puzzleSpace").add(s);
		
		exitButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	//RootPanel.get("puzzleSpace").remove(0);
            	//Solver s = new Solver();
            	((Solver) RootPanel.get("puzzleSpace").getWidget(0))
					.clear();
        		RootPanel.get("puzzleSpace").add(s);
            	workspaceId="";
                RootPanel.get("puzzleMenu").getElement().getStyle()
                	.setDisplay(Display.NONE);
                RootPanel.get("mainMenu").getElement().getStyle()
                	.setDisplay(Display.BLOCK);
                RootPanel.get("settingsMenu").getElement().getStyle()
                	.setDisplay(Display.NONE);
            }
        });
		settingsButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	RootPanel.get("settingsSpace").getElement().getStyle()
            		.setDisplay(Display.BLOCK);
            }
        });
		exitSettingsButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	RootPanel.get("settingsSpace").getElement().getStyle()
            		.setDisplay(Display.NONE);
            }
        });
	}
}
