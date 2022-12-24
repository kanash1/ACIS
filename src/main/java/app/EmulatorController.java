package app;

import cpu.CPU;
import cpu.Flag;
import javafx.scene.control.*;
import memory.cells.WordCell;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import memory.registers.Register32;
import memory.registers.StatusRegister;
import parsing.IncorrectFormatException;
import parsing.Parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmulatorController {
    @FXML
    public TextArea codeTextArea;
    @FXML
    public Button loadIntoMemoryButton;
    @FXML
    public ListView<WordCell> memoryListView;
    @FXML
    public Button importButton;
    @FXML
    public Button startButton;
    @FXML
    public Button nextButton;
    @FXML
    public Button resetButton;
    @FXML
    public ToggleButton trapToggleButton;
    @FXML
    public ListView<Register32> intRegsListView;
    @FXML
    public ListView<Register32> floatRegsListView;
    @FXML
    public ListView<Register32> programCounterListView;
    @FXML
    public ListView<StatusRegister> statusListView;

    private CPU cpu;
    private Parser parser;

    // TODO: это временно
    int count = 0;

    // TODO
    @FXML
    void initialize() {
        parser = new Parser();
        cpu = new CPU();
        memoryListView.setItems(FXCollections.observableList(cpu.memory.asWordList()));
        intRegsListView.setItems(FXCollections.observableList(cpu.intRegs));
        floatRegsListView.setItems(FXCollections.observableList(cpu.floatRegs));
        programCounterListView.setItems(FXCollections.observableList(new ArrayList<>(){{add(cpu.programCounter);}}));
        statusListView.setItems(FXCollections.observableList(new ArrayList<>(){{add(cpu.statusReg);}}));
        nextButton.setDisable(true);
        resetButton.setDisable(true);

        loadIntoMemoryButton.setOnAction(actionEvent -> {
            cpu.clear();
            String[] instructions = codeTextArea.getText().split("\n");
            // врменно
            count = instructions.length * 4;

            List<Integer> instructionsData = new ArrayList<>();

            try {
                for (var instruction : instructions) {
                    if (!instruction.isEmpty())
                        instructionsData.add(parser.parse(instruction));
                }

                for (int i = 0; i < instructionsData.size(); ++i)
                    memoryListView.getItems().get(i).setValue(instructionsData.get(i));

            } catch (IncorrectFormatException e) {
                showAlert(e.getMessage());
            }

            // временно
            cpu.intRegs.get(1).setValue(5);
            cpu.intRegs.get(2).setValue(6);

            refreshAll();
        });

        startButton.setOnAction(actionEvent -> {
            if (cpu.statusReg.getFlagStatus(Flag.TRAP)) {
                cpu.nextInstruction();
                startButton.setDisable(true);
                nextButton.setDisable(false);
            } else {
                while (cpu.programCounter.getValue() < count) {
                    cpu.nextInstruction();
                }
            }
            refreshAll();
        });

        resetButton.setOnAction(actionEvent -> {
            nextButton.setDisable(true);
            startButton.setDisable(false);
            resetButton.setDisable(true);
            cpu.clear();
        });

        importButton.setOnAction(actionEvent -> {

        });

        nextButton.setOnAction(actionEvent -> {
            if (cpu.statusReg.getFlagStatus(Flag.TRAP) && cpu.programCounter.getValue() < count) {
                cpu.nextInstruction();
            }
            else {
                while (cpu.programCounter.getValue() < count) {
                    cpu.nextInstruction();
                }
            }
            refreshAll();
        });

        trapToggleButton.setOnAction(actionEvent -> {
            cpu.statusReg.invertFlagStatus(Flag.TRAP);
            refreshAll();
        });
    }

    private void showAlert(String alertInfo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(alertInfo);
        alert.showAndWait();
    }

    private void refreshAll() {
        memoryListView.refresh();
        intRegsListView.refresh();
        floatRegsListView.refresh();
        programCounterListView.refresh();
        statusListView.refresh();
    }
}