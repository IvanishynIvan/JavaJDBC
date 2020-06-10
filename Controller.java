
     * Повернення першого об'єкту таблиці CarService
     */
    @FXML
    private void getFirstService() throws SQLException {
        currentCarServiceNumber = 0;
        getCarService();
    }
    /**
     * Повернення попереднього об'єкту таблиці CarService
     */
    @FXML
    private void getPrevService() throws SQLException {
        currentCarServiceNumber--;
        getCarService();
    }

    /**
     * Повернення наступного об'єкту таблиці CarService
     */
    @FXML
    private void getNextService() throws SQLException {
        currentCarServiceNumber++;
        getCarService();
    }

    /**
     * Повернення останнього об'єкту таблиці CarServer
     */
    @FXML
    private void getLastService() throws SQLException {
        currentCarServiceNumber = SQLService.getCarServiceCount() - 1;
        getCarService();
    }

    /**
     * Перевірка кнопок навігації об'єктів Log
     */
    private void checkLogNavigationBtns() {
        prevLogBtn.setDisable(currentLogNumber == 0);
        nextLogBtn.setDisable(currentLogNumber == SQLService.getLogCount() - 1);
        firstLogBtn.setDisable(currentLogNumber == 0);
        lastLogBtn.setDisable(currentLogNumber == SQLService.getLogCount() - 1);
    }
    /**
     * Повернення об'єкту таблиці Log
     */
    @FXML
    private void getLog(){
        checkLogNavigationBtns();
        setCurrentLogFields();
    }
    /**
     * Повернення першого об'єкту таблиці Log
     */
    @FXML
    private void getFirstLog() throws SQLException {
        currentLogNumber = 0;
        getLog();
    }
    /**
     * Повернення попереднього об'єкту таблиці Log
     */
    @FXML
    private void getPrevLog() throws SQLException {
        currentLogNumber--;
        getLog();
    }
    /**
     * Повернення наступного об'єкту таблиці Log
     */
    @FXML
    private void getNextLog() throws SQLException {
        currentLogNumber++;
        getLog();
    }

    /**
     * Повернення останього об'єкту таблиці Log
     */
    @FXML
    private void getLastLog() throws SQLException {
        currentLogNumber = SQLService.getLogCount() - 1;
        getLog();
    }

    /**
     * Додавання нового об'єкту в таблицю CarService
     */
    @FXML
    private void add() throws NumberFormatException {
        try {
            CarServiceChild newCarService = new CarServiceChild();
            newCarService.setNumberofCar(Integer.parseInt(carnumber.getText()));
            newCarService.setMarka(marka.getText());
            newCarService.setProbig(Integer.parseInt(probig.getText()));
            newCarService.setMaister(maister.getText());
            newCarService.setPrice(Integer.parseInt(price.getText()));
            SQLService.insertIntoCarServiceTable(newCarService);
            String str = "New Service added!\n";
            this.exceptionInfo += str;
            listLogs.appendText(str);
            SQLService.insertIntoLogTable(str);
        } catch (NumberFormatException | SQLException e) {
            String str2 = "Unable to add new CarService! Incorrect input!\n";
            try {
                this.exceptionInfo += str2;
                listLogs.appendText(str2);
                SQLService.insertIntoLogTable(str2);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * Модифікація об'єкту таблиці CarService
     */
    @FXML
    private void modify() throws NumberFormatException, SQLException {
        try {
            currentService.setNumberofCar(Integer.parseInt(carnumber.getText()));
            currentService.setMarka(marka.getText());
            currentService.setProbig(Integer.parseInt(probig.getText()));
            currentService.setMaister(maister.getText());
            currentService.setPrice(Integer.parseInt(price.getText()));
            SQLService.update(currentService);
            String str = "CarService updated!\n";
            this.exceptionInfo += str;
            listLogs.appendText(str);
            SQLService.insertIntoLogTable(str);
        } catch (NumberFormatException e) {
            String str2 = "Unable to modify CarService! Incorrect input!\n";
            this.exceptionInfo += str2;
            listLogs.appendText(str2);
            SQLService.insertIntoLogTable(str2);
        }
    }

    /**
     * Видалення об'єкту таблиці CarService
     */
    @FXML
    private void delete() throws Exception, SQLException {
        try {
            if (currentCarServiceNumber == SQLService.getCarServiceCount() - 1) {
                SQLService.delete(currentService);
                currentCarServiceNumber--;
                getCarService();
            } else {
                SQLService.delete(currentService);
                getCarService();
            }
            String str = "CarService was deleted!\n";
            this.exceptionInfo += str;
            listLogs.appendText(str);
            SQLService.insertIntoLogTable(str);
        } catch (Exception | SQLException e) {
            String str2 = "Unable to delete! " + e.getMessage();
            this.exceptionInfo += str2;
            listLogs.appendText(str2);
            SQLService.insertIntoLogTable(str2);
        }
        checkCarServiceSize();
    }

    /**
     * Видалення всіх об'єктів таблиці CarService
     */
    @FXML
    private void deleteAll() throws Exception, SQLException {
        SQLService.deleteAll();
        String str = "All CarService were deleted!\n";
        this.exceptionInfo += str;
        listLogs.appendText(str);
        SQLService.insertIntoLogTable(str);
        checkCarServiceSize();
    }

    /**
     * Перевірка створення таблиць
     */
    private void areTablesCreated() {
        if (isCarServiceTableCreated && isLogTableCreated) {
            loadToSqlBtn.setDisable(false);
            showCarServiceTableBtn.setDisable(false);
            showLogTableBtn.setDisable(false);
            createCarServiceTableBtn.setDisable(true);
            createLogTableBtn.setDisable(true);
        }
    }

    /**
     * Перевірка виведення таблиці CarService
     */
    private void isCarServiceTableShown() throws SQLException {
        if (SQLService.getCarServiceCount() != 0) {
            showCarServiceTableBtn.setDisable(true);
            modifyBtn.setDisable(false);
            addBtn.setDisable(false);
            deleteBtn.setDisable(false);
            deleteAllBtn.setDisable(false);
        }
    }

    /**
     * Перевірка розміру таблиці CarService
     */
    private void checkCarServiceSize() throws SQLException {
        if (SQLService.getCarServiceCount() == 0) {
            carnumber.setText("");
            marka.setText("");
            probig.setText("");
            maister.setText("");
            price.setText("");
            currentCarServiceNumber = 0;
            prevServiceBtn.setDisable(true);
            nextServiceBtn.setDisable(true);
            firstServiceBtn.setDisable(true);
            lastServiceBtn.setDisable(true);
            modifyBtn.setDisable(true);
            addBtn.setDisable(true);
            deleteBtn.setDisable(true);
            deleteAllBtn.setDisable(true);
            showCarServiceTableBtn.setDisable(false);
        }
    }

    /**
     * Відкриття FileChooser для збереження log файла.
     * @param event - це клас чи подія, яка отримуює повні посилання, коли подія буде виконаною
     */
    public void handleSaveLogFile(ActionEvent event)  throws Exception,SQLException {
        Stage secondaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Log File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Log", "*.log"));
        Exception.FileName = fileChooser.showSaveDialog(secondaryStage);
        String str = "The file "+ Exception.FileName.getName() + " was saved!\n";
        this.exceptionInfo +=  str;
        listLogs.appendText(str);
        SQLService.insertIntoLogTable(str);
        Exception.ErrInfo(exceptionInfo);
    }

    /**
     * Відкриття вікна "About"
     * @param event - це клас чи подія, яка отримуює повні посилання, коли подія буде виконаною
     */
    public void handleAbout(ActionEvent event) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("About the program and the author");
        dialog.setHeaderText("Practical work # 3.\r\n" +
                "Working with a relational database."
                + " ");
        dialog.setContentText("The author of the work - Ivanishyn Ivan Volodumurovych");
        dialog.showAndWait();
    }

    /**
     * Закриття програми
     * @param event - це клас чи подія, яка отримуює повні посилання, коли подія буде виконаною
     */
    public void closeApp(ActionEvent event) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm", ButtonType.OK, ButtonType.CANCEL);
        Stage stage = (Stage) fileMenu.getScene().getWindow();
        exitAlert.setContentText("Are you sure you want to exit?");
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.initOwner(stage);
        exitAlert.showAndWait();

        if(exitAlert.getResult() == ButtonType.OK) {
            Platform.exit();
        }
        else {
            exitAlert.close();
        }
    }
}
