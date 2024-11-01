package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.dto.Lottos;
import lotto.enums.LottoConfig;
import lotto.model.Lotto;
import lotto.validator.ServiceValidator;

public class WinService {

    private final Lottos lottos;
    private final ServiceValidator serviceValidator;

    private List<Integer> winNum = new ArrayList<>();
    private Integer bonusNum;

    public WinService(Lottos lottos, ServiceValidator serviceValidator) {
        this.lottos = lottos;
        this.serviceValidator = serviceValidator;
    }

    public void inputWinNum(List<Integer> winNum) {
        serviceValidator.winNumDup(winNum);
        this.winNum = winNum;
    }

    public void inputBonusNum(Integer bonusNum) {
        serviceValidator.bonusNumDup(winNum, bonusNum);
        this.bonusNum = bonusNum;
    }

    public void checkLottosWin() {
        for (Lotto lotto : lottos.getLottos()) {
            lotto.inputWin(winNum, bonusNum);
        }
    }

    public List<Integer> getLottosWin() {
        List<Integer> lottosWin = new ArrayList<>(List.of(0, 0, 0, 0, 0));
        for (Lotto lotto : lottos.getLottos()) {
            if (lotto.getWin() == null) {
                continue;
            }
            int index = lotto.getWin().getRank();
            lottosWin.set(index, lottosWin.get(index) + 1);
        }
        return lottosWin;
    }

    public Double getWinningsRate() {
        long totalWinnings = 0L;
        for (Lotto lotto : lottos.getLottos()) {
            if (lotto.getWin() == null) {
                continue;
            }
            totalWinnings += lotto.getWin().getWinnings();
        }

        double winningsRate
                = (double) totalWinnings / (lottos.getLottos().size() * LottoConfig.LOTTO_PRICE.getValue()) * 100;
        return Math.round(winningsRate * 10) / 10.0;
    }
}