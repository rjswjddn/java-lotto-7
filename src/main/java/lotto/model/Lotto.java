package lotto.model;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    private Win win;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    // TODO: 추가 기능 구현
    public List<Integer> getNumbers() {
        return numbers;
    }

    public void inputWin(List<Integer> winNum) {
        this.win = Win.getWin(checkWin(winNum));
    }

    private Integer checkWin(List<Integer> winNum) {
        int containCount = 0;
        for(Integer num : winNum) {
            if(numbers.contains(num)) {
                containCount++;
            }
        }
        return containCount;
    }

    public Win getWin() {
        return win;
    }
}
