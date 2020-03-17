package cursoandroid.whatsappandroid.com.netflix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cursoandroid.whatsappandroid.com.netflix.model.Movie;

public class MainActivity extends AppCompatActivity {

    MainAdapter mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       RecyclerView rv = findViewById(R.id.reclycler_view_main);


        ArrayList <Movie> movies = new ArrayList<>();

        for(int i = 0; i <30; i++) {
             Movie movie = new Movie();
             movie.setCoverUrl("ABC" + i);
             movies.add(movie);
        }

        mainAdapter = new MainAdapter(movies);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(mainAdapter);
    }

    private static class MovieHolder extends RecyclerView.ViewHolder{

        TextView textViewUrl;

        public MovieHolder(@NonNull View itemView){
            super (itemView);
            textViewUrl = itemView.findViewById(R.id.text_view_url);
        }
    }

    private class MainAdapter extends RecyclerView.Adapter<MovieHolder>{

        private final List<Movie> movies;
        public MainAdapter(List<Movie> movies){
            this.movies = movies;
        }
        @NonNull
        @Override
        public MovieHolder onCreateViewHolder(@NonNull ViewGroup  viewGroup, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.movie_item, viewGroup, false);
            return new MovieHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
            Movie movie = movies.get(i);
            movieHolder.textViewUrl.setText(movie.getCoverUrl());

        }

        @Override
        public int getItemCount() {
            return movies.size();
        }
    }
}
