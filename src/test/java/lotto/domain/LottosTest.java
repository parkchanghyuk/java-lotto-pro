package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class LottosTest {

	private Lottos lottos;

	@BeforeEach
	void setUp() {
		List<Lotto> lottosStuff = new ArrayList<>();
		lottosStuff.add(
			new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)
				.stream()
				.map(LottoNumber::new)
				.collect(Collectors.toSet())
			)
		);
		lottosStuff.add(
			new Lotto(Arrays.asList(1, 2, 3, 4, 5, 10)
				.stream()
				.map(LottoNumber::new)
				.collect(Collectors.toSet())
			)
		);
		lottos = new Lottos(lottosStuff);
	}

	@DisplayName("로또 크기 반환")
	@Test
	void quantity() {
		int lottoQuantity = lottos.quantity();

		assertThat(lottoQuantity).isEqualTo(2);
	}

	@DisplayName("로또 순위 match")
	@ParameterizedTest
	@EnumSource(names = {"FIRST_PLACE", "SECOND_PLACE"})
	void lottosRankMatch(Rank rank) {
		Lotto winninglotto = new Lotto(
			Arrays.asList(1, 2, 3, 4, 5, 6)
				.stream()
				.map(LottoNumber::new)
				.collect(Collectors.toSet()));

		List<Rank> ranks = lottos.match(winninglotto);

		assertThat(ranks).contains(rank);
	}
}
