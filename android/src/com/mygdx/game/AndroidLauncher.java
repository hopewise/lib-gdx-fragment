package com.mygdx.game;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication.Callbacks;
import com.badlogic.gdx.utils.Timer;


public class AndroidLauncher extends FragmentActivity implements Callbacks {
	private GameFragment fragment;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 6. Finally, replace the AndroidLauncher activity content with the Libgdx Fragment.
		fragment = new GameFragment();
		FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
		trans.replace(android.R.id.content, fragment);
		trans.commit();

	}



	// 4. Create a Class that extends AndroidFragmentApplication which is the Fragment implementation for Libgdx.
	public static class GameFragment extends AndroidFragmentApplication implements ICreator
	{
		public MyGdxGame myGdxGame;
		// 5. Add the initializeForView() code in the Fragment's onCreateView method.
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			myGdxGame= new MyGdxGame(this);
			return initializeForView(myGdxGame);
		}

		@Override
		public void LibGDXInied() {
			Timer.schedule(new Timer.Task(){
							   @Override
							   public void run() {
								   myGdxGame.rotate();
							   }
						   }
					, 1
					, 0.01f
			);
		}
	}


	@Override
	public void exit() {}





	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {

	}
}
