package imatloc.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName(this, TabView.class.getName());
        startActivity(intent);
    }
}