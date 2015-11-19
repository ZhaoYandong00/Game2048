package zyd.com.game2048;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public MainActivity() {
        mainActivity = this;
    }

    private static MainActivity mainActivity = null;
    private int score = 0;
    private int hScore = 0;
    private TextView tvScore;
    private TextView love;
    private TextView tvHscore;


    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore = (TextView) findViewById(R.id.tvScore);
        love = (TextView) findViewById(R.id.love);
        tvHscore = (TextView) findViewById(R.id.tvHscore);
        Context ctx = MainActivity.this;
        SharedPreferences sp = ctx.getSharedPreferences("SP", MODE_PRIVATE);
        hScore = sp.getInt("hscore", 0);
        showHighScore();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showHighScore() {
        tvHscore.setText(hScore + "");
    }

    public int gethScore() {
        return hScore;
    }

    public void sethScore() {
        hScore=score;
        SharedPreferences sp = getSharedPreferences("SP", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("hscore",hScore);
        editor.apply();
    }

    public void clearScore() {
        score = 0;
        showScore();
        showHighScore();
        love.setBackgroundColor(0x00FFFFFF);
        love.setText("达到2048有惊喜！！！");
    }

    public void showScore() {
        tvScore.setText(score + "");
    }

    public int getScore() {
        return score;
    }

    public void addScore(int s) {
        score += s;
        if (score >= 2048 && score < 10000) {
            showSurprised();
        }

        if (score > 10000) {
            love.setText("很好继续努力！");

        }


        showScore();
    }

    private void showSurprised() {
        love.setBackgroundColor(0xffffd700);
        love.setText("制作者：ZYD");
    }


}

