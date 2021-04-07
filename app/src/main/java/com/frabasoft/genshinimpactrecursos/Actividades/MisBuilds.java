package com.frabasoft.genshinimpactrecursos.Actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.frabasoft.genshinimpactrecursos.R;
import com.frabasoft.genshinimpactrecursos.TouchImage.TouchImageView;
import com.master.permissionhelper.PermissionHelper;

import java.io.IOException;
import java.text.DecimalFormat;

public class MisBuilds extends AppCompatActivity {
    private Spinner spPJMisBuilds;
    private TouchImageView imgPJMisBuilds;
    private Button guardarFlor,guardarPluma, guardarReloj, guardarCopa, guardarCorona, guardarTodo;
    private String[] personajesString = {"Seleccione",
            "Albedo", "Amber",
            "Barbara", "Beidou", "Bennet",
            "Chongyun", "Diluc",
            "Diona", "Fischl", "Ganyu",
            "Hu Tao", "Jean", "Kaeya",
            "Keqing", "Klee", "Lisa",
            "Mona", "Ninguang",
            "Noelle", "Qiqi", "Razor",
            "Rosaria",
            "Sucrose","Tartaglia",
            "Venti", "Xianling",
            "Xiao", "Xingqiu",
            "Xinyan","Zhongli"};

    private DecimalFormat df;
    PermissionHelper permissionHelper;
    private final static String TAG = "ERRORTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_builds);

        ejecutar();

        spPJMisBuilds = (Spinner)findViewById(R.id.spPJMisBuilds);
        imgPJMisBuilds = (TouchImageView)findViewById(R.id.imgPJMisBuilds);
        df = new DecimalFormat("#.##");
        
        spPJMisBuilds.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, personajesString));
        spPJMisBuilds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    imgPJMisBuilds.setImageResource(R.drawable.wallpaper);
                } else if (position == 1) {
                    imgPJMisBuilds.setImageResource(R.drawable.albedobuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                } else if (position == 2) {
                    imgPJMisBuilds.setImageResource(R.drawable.amberbuilds);

                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                } else if (position == 3) {
                    imgPJMisBuilds.setImageResource(R.drawable.barbarabuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                } else if (position == 4) {
                    imgPJMisBuilds.setImageResource(R.drawable.beidoubuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 5) {
                    imgPJMisBuilds.setImageResource(R.drawable.bennetbuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 6) {
                    imgPJMisBuilds.setImageResource(R.drawable.chongyunbuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 7) {
                   
                    imgPJMisBuilds.setImageResource(R.drawable.dilucbuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 8) {
                    
                    imgPJMisBuilds.setImageResource(R.drawable.dionabuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 9) {
                   
                    imgPJMisBuilds.setImageResource(R.drawable.fischlbuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 10) {
                    
                    imgPJMisBuilds.setImageResource(R.drawable.ganyubuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 11) {
                   
                    imgPJMisBuilds.setImageResource(R.drawable.hutaobuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 12) {
                    
                    imgPJMisBuilds.setImageResource(R.drawable.jeanbuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 13) {
                    
                    imgPJMisBuilds.setImageResource(R.drawable.kaeyabuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 14) {
                   
                    imgPJMisBuilds.setImageResource(R.drawable.keqingbuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 15) {
                    imgPJMisBuilds.setImageResource(R.drawable.kleebuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 16) {
                    imgPJMisBuilds.setImageResource(R.drawable.lisabuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 17) {
                    
                    imgPJMisBuilds.setImageResource(R.drawable.monabuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 18) {
                    
                    imgPJMisBuilds.setImageResource(R.drawable.ningguangbuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 19) {
                    imgPJMisBuilds.setImageResource(R.drawable.noellebuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 20) {
                    imgPJMisBuilds.setImageResource(R.drawable.qiqibuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 21) {
                    imgPJMisBuilds.setImageResource(R.drawable.razorbuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 22) {
                    imgPJMisBuilds.setImageResource(R.drawable.rosariabuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 23) {
                   
                    imgPJMisBuilds.setImageResource(R.drawable.qiqibuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 24) {
                    
                    imgPJMisBuilds.setImageResource(R.drawable.tartagliabuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 25) {
                    
                    imgPJMisBuilds.setImageResource(R.drawable.ventibuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 26) {
                    imgPJMisBuilds.setImageResource(R.drawable.xianlingbuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 27) {
                    
                    imgPJMisBuilds.setImageResource(R.drawable.xiaobuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 28) {
                    
                    imgPJMisBuilds.setImageResource(R.drawable.xingqiubuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            
                            return true;
                        }
                    });
                } else if (position == 29) {
                    
                    imgPJMisBuilds.setImageResource(R.drawable.xinyanbuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                } else if (position == 30) {
                    imgPJMisBuilds.setImageResource(R.drawable.zhonglibuilds);
                    imgPJMisBuilds.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            return true;
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void ejecutar(){
        permissionHelper = new PermissionHelper(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        permissionHelper.request(new PermissionHelper.PermissionCallback() {
            @Override
            public void onPermissionGranted() {
                Log.d(TAG, "onPermissionGranted() called");
            }

            @Override
            public void onIndividualPermissionGranted(String[] grantedPermission) {
                Log.d(TAG, "onIndividualPermissionGranted() called with: grantedPermission = [" + TextUtils.join(",",grantedPermission) + "]");
            }

            @Override
            public void onPermissionDenied() {
                Log.d(TAG, "onPermissionDenied() called");
            }

            @Override
            public void onPermissionDeniedBySystem() {
                Log.d(TAG, "onPermissionDeniedBySystem() called");
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionHelper != null) {
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}