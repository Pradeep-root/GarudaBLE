package com.pradeep.garudable;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.util.List;

public class GarudaBel implements GarudaBle {

    private BluetoothLeScanner bluetoothLeScanner;
    private boolean scanning;
    private Handler handler = new Handler();

    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 100000;


    public GarudaBel(Context context) {
        final BluetoothManager bluetoothManager =
                (BluetoothManager) context.getApplicationContext().getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();

        bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
    }

    @Override
    public void setBleConfiguration() {

    }

    @Override
    public void startBleScan() {
        if (!scanning) {
            // Stops scanning after a predefined scan period.
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    scanning = false;
                    stopBleScan();
                }
            }, SCAN_PERIOD);

            scanning = true;
            bluetoothLeScanner.startScan(scanCallback);
        } else {
            scanning = false;
            bluetoothLeScanner.stopScan(scanCallback);
        }
    }

    @Override
    public void stopBleScan() {
        bluetoothLeScanner.stopScan(scanCallback);
    }

    @Override
    public void connectBle() {

    }

    ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            Log.d("GarudaBle", ""+result.toString());
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
        }
    };



}
