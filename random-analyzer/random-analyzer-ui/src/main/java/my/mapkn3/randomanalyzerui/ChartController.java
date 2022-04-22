package my.mapkn3.randomanalyzerui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import lombok.RequiredArgsConstructor;
import my.mapkn3.randomizerclient.client.RandomizerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static javafx.collections.FXCollections.observableArrayList;

@RequiredArgsConstructor
@Component
public class ChartController {
    @FXML
    public BarChart<String, Integer> chart;
    private final RandomizerClient randomizerClient;

    @FXML
    public void initialize() {
        RandomIntSubscriber tenSubscriber1 = new RandomIntSubscriber(10);
        randomizerClient.ints(tenSubscriber1.getNumberCount())
                .subscribe(tenSubscriber1);

        RandomIntSubscriber tenSubscriber2 = new RandomIntSubscriber(10);
        randomizerClient.ints(tenSubscriber2.getNumberCount())
                .subscribe(tenSubscriber2);

        ObservableList<Series<String, Integer>> data = observableArrayList();
        data.add(tenSubscriber1.getSeries());
        data.add(tenSubscriber2.getSeries());
        chart.setData(data);
    }

    private static class RandomIntSubscriber implements Consumer<Integer> {
        private final int numberCount;
        private final ObservableList<Data<String, Integer>> seriesData;
        private final Series<String, Integer> series;

        private RandomIntSubscriber(int numberCount) {
            this.numberCount = numberCount;
            List<Data<String, Integer>> initData = IntStream.range(0, this.numberCount)
                    .boxed()
                    .map(i -> new Data<>(Integer.toString(i), 0))
                    .collect(toList());
            this.seriesData = observableArrayList(initData);
            this.series = new Series<>("Random for " + numberCount, seriesData);
        }

        @Override
        public void accept(Integer value) {
            Platform.runLater(() -> {
                Data<String, Integer> data = seriesData.get(value);
                data.setYValue(data.getYValue() + 1);
            });
        }

        public int getNumberCount() {
            return numberCount;
        }

        public Series<String, Integer> getSeries() {
            return series;
        }
    }
}