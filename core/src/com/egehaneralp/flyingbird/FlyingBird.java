package com.egehaneralp.flyingbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlyingBird extends ApplicationAdapter {
	SpriteBatch batch; //batch=yığın
	Texture img; //texture = yapı
	Texture bird,bird2;
	Texture enemy1;
	float bx,by,bw,bh;
	float ex,ey;
	float sw,sh;//screenwidth - screenheight
	float gravity=0.6f; //0.s							//TANIMLAMALAR YAPILIR
	float v=0.0f;
	int state=0;
	int wings=0;

	static int enemynum=1;
	float enemyX[]=new float[enemynum];
	float dist; // ENEMYLER ARASI UZAKLIK


	@Override
	public void create () {          					// ATAMALAR YAPILIR
		batch = new SpriteBatch();
		img = new Texture("storyboard6.png");
		bird = new Texture("frame-1.png");
		bird2 = new Texture("frame-3.png");
		enemy1= new Texture("sprite1.png");

		bx=Gdx.graphics.getWidth()/4;
		by=Gdx.graphics.getHeight()/3;
		bw=Gdx.graphics.getWidth()/12; // sw/13
		bh=Gdx.graphics.getHeight()/9; // sw/10
		sw=Gdx.graphics.getWidth();
		sh=Gdx.graphics.getHeight();
		ex=2000;
		ey=500;
		dist =Gdx.graphics.getWidth()/2; // ARILAR ARASI YARIM EKRAN BOŞLUK

		for(int i=0;i<enemynum;i++){
			enemyX[i]=Gdx.graphics.getWidth()+(i*dist);
		}
	}

	@Override
	public void render () {								// FRONTEND KISMI
		batch.begin();
		batch.draw(img,0,0,sw,sh);
		batch.draw(enemy1,ex,ey,bw,bh);

		if(Gdx.input.justTouched()){
			state=1;

		}
		if(state==1){

			/*ex-=10;
			if(ex<=-150){
				ex=2000;
			}
			*/
			for(int i=0;i<enemynum;i++){ //v=-17 nin altına yazdı.........
				if(enemyX[i]<=-150){
					enemyX[i]= enemyX[i] + enemynum*dist*2 +150;
				}
				enemyX[i]-=10;
				batch.draw(enemy1,enemyX[i],ey,bw,bh);

			}

			if(Gdx.input.justTouched()){  //bir kere daha basmak (zıplatmak)
				v=-17;						// by = by-v;  (y ekseni koordinatini arttırmak) (-17)
				wings--;
			}

			if(by >= sh-50 ){
				v+=22;					//KUŞ TEPEYE GELDİGİNDE SEKMESİ İÇİN CODE BLOĞU ??????
			}

			if(by<=0){
				state=2;    				//  ::::STATE2 -> CANI 1 AZALT STATE=1 YAP :::::
				by=Gdx.graphics.getHeight()/3;
			}
			else{
				v += gravity;				//GRAVİTY -> BİR DEGER TUTUYOR
				by -= v;					//V (HIZ) GRAVİTY KADAR ARTIYOR
			}                               //BY |'+'|'-'| V İLE DEĞİŞTİRİLİYOR...
											// GRAVİTY -> VELOCİTY -> Y-Axis

		}
		if (wings==1) {
			batch.draw(bird, bx, by, bw, bh);

		}

		if(wings==0){
			batch.draw(bird2, bx, by, bw, bh);

			try{									//DELAY EKLEMEK!!!!!
				Thread.sleep(25);
			}catch(InterruptedException e){}

			wings++;
		}

		/*try{
			Thread.sleep(100);
		}catch(InterruptedException e){}*/

		//batch.draw(bird, bx, by, bw, bh);
		batch.end();
	}
	
	@Override
	public void dispose () {

	}
}
