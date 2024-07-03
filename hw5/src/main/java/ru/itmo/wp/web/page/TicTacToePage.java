package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {
    public static class State {
        private final String[][] cells;
        private final int size;
        private boolean crossesMove;
        private boolean isBusyCell;
        private int countOfAvailableCells;
        private String phase;

        private State(int size) {
            this.cells = new String[size][size];
            this.size = size;
            this.countOfAvailableCells = size * size;
            this.phase = "RUNNING";
            this.crossesMove = true;
        }

        public static State newState(int size) {
            return new State(size);
        }

        public int getSize() {
            return size;
        }

        public String[][] getCells() {
            return cells;
        }

        public String getPhase() {
            return phase;
        }

        public int getCountOfAvailableCells() {
            return countOfAvailableCells;
        }

        public boolean getCrossesMove() {
            return crossesMove;
        }

        private boolean coordsGood(int x, int y) {
            return x >= 0 && x < size && y >= 0 && y < size;
        }

        private String getCurrentTurn() {
            return crossesMove ? "X" : "O";
        }

        private boolean checkInOneDir(int x, int y, int x_, int y_) {
            int counter = 0;
            for (int i = x, j = y; coordsGood(i, j); i += x_, j += y_) {
                if (getCurrentTurn().equals(cells[i][j])) {
                    counter++;
                }
            }
            return counter == size;
        }
        private boolean isWin() {
            for (int i = 0; i < size; i++) {
                if (checkInOneDir(i, 0, 0, 1) || checkInOneDir(0, i, 1, 0)) {
                    return true;
                }
            }
            return checkInOneDir(0, 0, 1, 1) || checkInOneDir(0, size - 1, -1, 1);
        }
        private void doMove(int x, int y) {
            if (cells[x][y] == null) {
                String currSign = crossesMove ? "X" : "O";
                cells[x][y] = currSign;
                countOfAvailableCells--;
                if (isWin()) {
                    phase = "WON_" + currSign;
                } else if (countOfAvailableCells == 0) {
                    phase = "DRAW";
                }
                crossesMove = !crossesMove;
            }
        }
    }

    private void action(Map<String, Object> view, HttpServletRequest request) {
        if (request.getSession().getAttribute("state") == null) {
            State state = State.newState(3);
            view.put("state", state);
            request.getSession().setAttribute("state", state);
        } else {
            view.put("state", request.getSession().getAttribute("state"));
        }
    }

    private void onMove(Map<String, Object> view, HttpServletRequest request) {
        State state = (State) request.getSession().getAttribute("state");
        if (state == null) {
            state = new State(3);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (request.getParameter("cell_" + i + j) != null && state.getPhase().equals("RUNNING")) {
                    state.doMove(i, j);
                }
            }
        }
        request.setAttribute("state", state);
        action(view, request);
    }

    private void newGame(Map<String, Object> view, HttpServletRequest request) {
        request.getSession().setAttribute("state", new State(3));
        action(view, request);
    }
}
