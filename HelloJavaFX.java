import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

import java.util.Optional;

public class HelloJavaFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Font font1 = Font.loadFont(getClass().getResourceAsStream("STCAIYUN.TTF"), 12);
        Font font2 = Font.loadFont(getClass().getResourceAsStream("simsun.ttc"), 12);
        Button btn = new Button();
        btn.setText("Hello,JavaFX");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello,JavaFX!");

//                Dialog<ButtonType> dialog = new Dialog<ButtonType>();
//                dialog.initOwner(primaryStage.getOwner());
//                //dialog.getDialogPane().getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//                //dialog.initStyle(StageStyle.UTILITY);
//                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
//                dialog.initModality(Modality.NONE);
//                dialog.setHeaderText("Header Text");
//                dialog.setContentText("单击按钮");
//                dialog.setTitle("提示");
//                dialog.showAndWait();
//                dialog.close();


//                Stage s = new Stage();
//                s.initModality(Modality.APPLICATION_MODAL);
//                StackPane pp = new StackPane();
//
//                Button btn = new Button();
//                btn.setText("Close");
//                btn.setOnAction(event1 -> {
//                    //s.hide();
//                    s.close();
//                });
//                pp.getChildren().add(btn);
//                Scene ss = new Scene(pp);
//                s.setScene(ss);
//                s.setTitle("Test");
//                //s.initStyle(StageStyle.UNIFIED);
//                //s.resizableProperty().setValue(Boolean.FALSE);
//                s.getIcons().add(new Image(this.getClass().getResource("xingqiu.png").toString()));
//                s.setResizable(false);
//                s.setHeight(200);
//                s.setWidth(300);
//                s.showAndWait();


                Dialog<Pair<String, String>> dialog = new Dialog<>();
                dialog.initOwner(primaryStage);

                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                // Add a custom icon.
                stage.getIcons().add(new Image(this.getClass().getResource("xingqiu.png").toString()));

                dialog.initStyle(StageStyle.UTILITY);

                dialog.setTitle("自定义对话框");
                dialog.setHeaderText("头部内容");

                dialog.setGraphic(new ImageView(this.getClass().getResource("xingqiu.png").toString()));

                ButtonType loginButtonType = new ButtonType("登录", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                TextField name = new TextField();
                name.setPromptText("用户名");
                PasswordField password = new PasswordField();
                password.setPromptText("密码");

                grid.add(new Label("用户名:"), 0, 0);
                grid.add(name, 1, 0);
                grid.add(new Label("密码:"), 0, 1);
                grid.add(password, 1, 1);

                Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
                loginButton.setDisable(true);

                // 是哟功能 Java 8 lambda 表达式进行校验
                name.textProperty().addListener((observable, oldValue, newValue) -> {
                    loginButton.setDisable(newValue.trim().isEmpty() || password.getText().trim().isEmpty());
                });

                password.textProperty().addListener((observable, oldValue, newValue) -> {
                    loginButton.setDisable(newValue.trim().isEmpty() || name.getText().trim().isEmpty());
                });

                dialog.getDialogPane().setContent(grid);

                // 默认光标在用户名上
                Platform.runLater(() -> name.requestFocus());

                // 登录按钮后，将结果转为username-password-pair
                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == loginButtonType) {
                        return new Pair<>(name.getText(), password.getText());
                    }
                    return null;
                });
                Optional<Pair<String, String>> result = dialog.showAndWait();

//                Alert alert = new Alert(Alert.AlertType.INFORMATION); // 创建一个消息对话框
//                alert.setHeaderText("今日天气"); // 设置对话框的头部文本
//                // 设置对话框的内容文本
//                alert.setContentText("今天白天晴转多云，北转南风2、3间4级，最高气温28℃；夜间多云转阴，南风2级左右，最低气温16℃。");
//                alert.show(); // 显示对话框
            }
        });
//        btn.setOnAction(event -> {
//            System.out.println("Hello,JavaFX!");
//        });
//        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println("Hello,JavaFX!");
//            }
//        });
        BorderPane root = new BorderPane();
        //root.getChildren().add(btn);
        root.setBottom(btn);

        Button btn2 = new Button("", new ImageView(this.getClass().getResource("xingqiu.png").toString()));
        btn2.setStyle("-fx-background-color: transparent; -fx-background-radius: 0em;");
        //btn2.setPadding(Insets.EMPTY);
        //btn2.setText("Hello,JavaFX");
        btn2.setPrefHeight(32);
        btn2.setPrefWidth(32);
        btn2.setMaxSize(32,32);
        root.setCenter(btn2);
        Scene scene = new Scene(root, 300, 250);
        String s = getClass().getResource("application.css").toExternalForm();
        //scene.getStylesheets().add(s);
        //primaryStage.setIconified(true);
        // primaryStage.initModality(Modality.NONE);

//        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//        primaryStage.setX(primaryScreenBounds.getMinX());
//        primaryStage.setY(primaryScreenBounds.getMinY());
//        primaryStage.setWidth(primaryScreenBounds.getWidth());
//        primaryStage.setHeight(primaryScreenBounds.getHeight());
//
//        primaryStage.initStyle(StageStyle.DECORATED);

        //primaryStage.resizableProperty().setValue(Boolean.FALSE);
        //primaryStage.initStyle(StageStyle.UTILITY);

        primaryStage.getIcons().add(new Image(this.getClass().getResource("xingqiu.png").toString()));
        primaryStage.setTitle("JavaFX");

        //scene.setFill(null);

        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        //StageStyle ss = primaryStage.getStyle();

        primaryStage.show();
        Platform.runLater(() -> {
//            try {
//                Thread.sleep(5000);
//            }catch (Exception eee){}
//
//            primaryStage.show();
        });
    }
}