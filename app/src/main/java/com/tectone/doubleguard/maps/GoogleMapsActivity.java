package com.tectone.doubleguard.maps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tectone.doubleguard.R;
import com.tectone.doubleguard.util.GpsInfo;

public class GoogleMapsActivity extends FragmentActivity implements OnMapClickListener {

    LatLng latLng;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Circle mCircle;
    private Marker mMarker;
    private ImageButton btn_mapseting;
    private double range_value = 500;
    private int range_choice_value = 1;
    View.OnClickListener btn_mapseting_click = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            dialogMapRange();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        init();
        // 터치이벤트 설정
        //여기서 터치이벤트를 설정할경우 ini에 넣어둔 setOnMapClickListener 안된
        // mMap.setOnMapClickListener(this); 이걸 안 쓸경우 OnMapClickListener 와 인터페이스 메소드 오버라이드 안 해도됨
        mMap.setOnMapClickListener(this);

        btn_mapseting = (ImageButton) findViewById(R.id.btn_mapseting);
        btn_mapseting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_mapseting.setOnClickListener(btn_mapseting_click);

        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    /**
     * 초기화
     *
     * @author gon 2014. 2. 16.
     */
    private void init() {

        Intent getI = getIntent();
        GooglePlayServicesUtil.isGooglePlayServicesAvailable(GoogleMapsActivity.this);
        mMap = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();

//        String title = getI.getStringExtra("marker test 1");
//        String coordinates[] = { "37.517180", "127.041268" };
//        double lat = Double.parseDouble(coordinates[0]);
//        double lng = Double.parseDouble(coordinates[1]);

//        LatLng position = new LatLng(lat, lng);
//        GooglePlayServicesUtil.isGooglePlayServicesAvailable(GoogleMapsActivity.this);
//        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        // 항공사진 변경
//        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // 맵 위치이동.
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));

        /** 사각형 그리기  */
        //this.setUpMap(lat, lng);

        /** 맵에 붙여 넣을 BitmapDescriptor 생성 */
        /*BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.id.map);

		// 붙여 넣기 옵션 설정
		LatLng sw = new LatLng(lat, lng);
		LatLng nw = new LatLng(lat, lng);
		LatLngBounds mapBounds = new LatLngBounds(sw, nw);

		GroundOverlayOptions options = new GroundOverlayOptions();
		options.image(descriptor);
		options.positionFromBounds(mapBounds);
		options.transparency(0.5f);
		//options.anchor(0, 1);
		//options.position(position, 80);

		// 맵에 알파값 설정
		GroundOverlay overlay = mGoogleMap.addGroundOverlay(options);
		overlay.setTransparency(0.5F);*/


        GpsInfo gps = new GpsInfo(GoogleMapsActivity.this);
        // GPS 사용유무 가져오기
        if (gps.isGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // Creating a LatLng object for the current location
            latLng = new LatLng(latitude, longitude);

            // Showing the current location in Google Map
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            // Map 을 zoom 합니다.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(18));
            mMap.getUiSettings().setIndoorLevelPickerEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(true);
            // 마커 설정.
            MarkerOptions optFirst = new MarkerOptions();
            optFirst.position(latLng);// 위도 ? 경도
            optFirst.title("현재위치");// 제목 미리보기

            //optFirst.snippet("Snippet");
            //optFirst.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
            mMap.addMarker(optFirst).showInfoWindow();

            //addCircle(latLng, 100);
            drawMarkerWithCircle(latLng, range_value);
        } else {
            gps.showSettingsAlert();
        }


        // 마커 클릭 리스너
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            public boolean onMarkerClick(Marker marker) {
                String text = "[마커 클릭 이벤트] latitude =" + marker.getPosition().latitude + ", longitude ="
                        + marker.getPosition().longitude;
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG)
                        .show();
                return false;
            }
        });

        // 클릭이벤트
        mMap.setOnMapClickListener(new OnMapClickListener() {

            public void onMapClick(LatLng point) {
                String text = "[단시간 클릭시 이벤트] latitude =" + point.latitude + ", longitude ="
                        + point.longitude;
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG)
                        .show();
            }
        });

        // 클릭을 오래 했을때
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            public void onMapLongClick(LatLng point) {
                String text = "[장시간 클릭시 이벤트] latitude =" + point.latitude + ", longitude ="
                        + point.longitude;
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG)
                        .show();
            }
        });

        // 카메라 이동 확대 축소가 있을때 이벤트 발생
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

            public void onCameraChange(CameraPosition position) {
                LatLng point = position.target;
                String text = "[카메라 이동 이벤트] latitude =" + point.latitude + ", longitude ="
                        + point.longitude;
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void init_old() {

        GooglePlayServicesUtil.isGooglePlayServicesAvailable(GoogleMapsActivity.this);
        mMap = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        // 터치이벤트 설정
        //mMap.setOnMapClickListener(this);
        // 맵의 이동
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));

        GpsInfo gps = new GpsInfo(GoogleMapsActivity.this);
        // GPS 사용유무 가져오기
        if (gps.isGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // Creating a LatLng object for the current location
            LatLng latLng = new LatLng(latitude, longitude);

            // Showing the current location in Google Map
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            // Map 을 zoom 합니다.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(18));
            mMap.getUiSettings().setIndoorLevelPickerEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(true);
            // 마커 설정.
            MarkerOptions optFirst = new MarkerOptions();
            optFirst.position(latLng);// 위도 ? 경도
            optFirst.title("현재위치");// 제목 미리보기

            //optFirst.snippet("Snippet");
            //optFirst.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
            mMap.addMarker(optFirst).showInfoWindow();

            //addCircle(latLng, 100);
            drawMarkerWithCircle(latLng, 100);
        } else {
            gps.showSettingsAlert();
        }
    }

    @Override
    public void onMapClick(LatLng point) {
        System.out.println("##############################" + point);
        // 현재 위도와 경도에서 화면 포인트를 알려준다
        Point screenPt = mMap.getProjection().toScreenLocation(point);

        // 현재 화면에 찍힌 포인트로 부터 위도와 경도를 알려준다.
        LatLng latLng = mMap.getProjection().fromScreenLocation(screenPt);

        Log.d("맵좌표", "좌표: 위도(" + String.valueOf(point.latitude) + "), 경도("
                + String.valueOf(point.longitude) + ")");
        Log.d("화면좌표", "화면좌표: X(" + String.valueOf(screenPt.x) + "), Y("
                + String.valueOf(screenPt.y) + ")");
    }

    private void addCircle(LatLng latLng, double radius) {
        double R = 6371d; // earth's mean radius in km
        double d = radius / R; //radius given in km
        double lat1 = Math.toRadians(latLng.latitude);
        double lon1 = Math.toRadians(latLng.longitude);
        PolylineOptions options = new PolylineOptions();
        for (int x = 0; x <= 360; x++) {
            double brng = Math.toRadians(x);
            double latitudeRad = Math.asin(Math.sin(lat1) * Math.cos(d) + Math.cos(lat1) * Math.sin(d) * Math.cos(brng));
            double longitudeRad = (lon1 + Math.atan2(Math.sin(brng) * Math.sin(d) * Math.cos(lat1), Math.cos(d) - Math.sin(lat1) * Math.sin(latitudeRad)));
            options.add(new LatLng(Math.toDegrees(latitudeRad), Math.toDegrees(longitudeRad)));
        }
        mMap.addPolyline(options.color(Color.RED).width(2));
    }

    private void updateMarkerWithCircle(LatLng position) {
        mCircle.setCenter(position);
        mMarker.setPosition(position);
    }

    private void drawMarkerWithCircle(LatLng position, double radius) {

        double radiusInMeters = radius; //100.0;
        int strokeColor = 0xffff0000; //red outline
        int shadeColor = 0x44ff0000; //opaque red fill

        CircleOptions circleOptions = new CircleOptions().center(position).radius(radiusInMeters).fillColor(shadeColor).strokeColor(strokeColor).strokeWidth(8);
        mCircle = mMap.addCircle(circleOptions);

        MarkerOptions markerOptions = new MarkerOptions().position(position);
        mMarker = mMap.addMarker(markerOptions);
    }

    private void dialogMapRange() {
        try {
            final CharSequence[] choice_items = {"500M", "1Km", "2Km", "3Km"};
            //AlertDialog.Builder ab = new AlertDialog.Builder(this.getActivity().getApplicationContext());
            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setTitle("이탈범위설정");
            ab.setSingleChoiceItems(choice_items, 0, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    System.out.println("whichButton===================>" + whichButton);
                    range_choice_value = whichButton;
                }
            }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    if (range_choice_value == 1) {
                        range_value = 500;
                    } else if (range_choice_value == 2) {
                        range_value = 1000;
                    } else if (range_choice_value == 3) {
                        range_value = 2000;
                    } else {
                        range_value = 3000;
                    }
                    //updateMarkerWithCircle(latLng);
                    drawMarkerWithCircle(latLng, range_value);
                    //init();
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Cancel 버튼 클릭시
                        }
                    }
            );

            ab.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
