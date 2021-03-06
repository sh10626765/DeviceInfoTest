package com.softterm.tools.deviceinfotest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button startTestButton = findViewById(R.id.button_start_test),
                fullCpuInfoButton = findViewById(R.id.button_full_cpu_info),
                fullMemInfoButton = findViewById(R.id.button_full_mem_info),
                resetButton = findViewById(R.id.button_reset);

        final TextView brand = findViewById(R.id.brand),
                manufacturer = findViewById(R.id.manufacturer),
                product = findViewById(R.id.product),
                model = findViewById(R.id.model),
                device = findViewById(R.id.device),
                board = findViewById(R.id.board),
                hardware = findViewById(R.id.hardware),
                cpuInfo = findViewById(R.id.cpu_info),
                ram = findViewById(R.id.ram),
                rom = findViewById(R.id.rom),
                androidVersion = findViewById(R.id.android_version),
                display = findViewById(R.id.display),
                h265Encode = findViewById(R.id.h265_encode),
                h265Decode = findViewById(R.id.h265_decode),
                networkType = findViewById(R.id.network_type);

        final TextView brandText = findViewById(R.id.text_brand),
                manufacturerText = findViewById(R.id.text_manufacturer),
                productText = findViewById(R.id.text_product),
                modelText = findViewById(R.id.text_model),
                deviceText = findViewById(R.id.text_device),
                boardText = findViewById(R.id.text_board),
                hardwareText = findViewById(R.id.text_hardware),
                cpuInfoText = findViewById(R.id.text_cpu_info),
                ramText = findViewById(R.id.text_ram),
                romText = findViewById(R.id.text_rom),
                androidVersionText = findViewById(R.id.text_android_version),
                displayText = findViewById(R.id.text_display),
                h265EncodeText = findViewById(R.id.text_h265_encode),
                h265DecodeText = findViewById(R.id.text_h265_decode),
                networkTypeText = findViewById(R.id.text_network_type);

        final TextView fullInfo = findViewById(R.id.text_detail);

        startTestButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                brand.setVisibility(View.VISIBLE);
                manufacturer.setVisibility(View.VISIBLE);
                product.setVisibility(View.VISIBLE);
                model.setVisibility(View.VISIBLE);
                device.setVisibility(View.VISIBLE);
                board.setVisibility(View.VISIBLE);
                hardware.setVisibility(View.VISIBLE);
                cpuInfo.setVisibility(View.VISIBLE);
                ram.setVisibility(View.VISIBLE);
                rom.setVisibility(View.VISIBLE);
                androidVersion.setVisibility(View.VISIBLE);
                display.setVisibility(View.VISIBLE);
                h265Decode.setVisibility(View.VISIBLE);
                h265Encode.setVisibility(View.VISIBLE);
                networkType.setVisibility(View.VISIBLE);

                brandText.setVisibility(View.VISIBLE);
                manufacturerText.setVisibility(View.VISIBLE);
                productText.setVisibility(View.VISIBLE);
                modelText.setVisibility(View.VISIBLE);
                deviceText.setVisibility(View.VISIBLE);
                boardText.setVisibility(View.VISIBLE);
                hardwareText.setVisibility(View.VISIBLE);
                cpuInfoText.setVisibility(View.VISIBLE);
                ramText.setVisibility(View.VISIBLE);
                romText.setVisibility(View.VISIBLE);
                androidVersionText.setVisibility(View.VISIBLE);
                displayText.setVisibility(View.VISIBLE);
                h265DecodeText.setVisibility(View.VISIBLE);
                h265EncodeText.setVisibility(View.VISIBLE);
                networkTypeText.setVisibility(View.VISIBLE);

                brandText.setText(Build.BRAND);
                manufacturerText.setText(Build.MANUFACTURER);
                productText.setText(Build.PRODUCT);
                modelText.setText(Build.MODEL);
                deviceText.setText(Build.DEVICE);
                boardText.setText(Build.BOARD);
                androidVersionText.setText(Build.VERSION.RELEASE);
                hardwareText.setText(Build.HARDWARE);
                displayText.setText(Build.DISPLAY);

                String cpuInfoStr = getInfo("cpu", false);
                if (cpuInfoStr != null && cpuInfoStr.equals("")) {
                    cpuInfoText.setText("CPU Info Not Found!");
                } else {
                    cpuInfoText.setText(cpuInfoStr);
                }

                ramText.setText(getTotalMemory());
                romText.setText(getTotalFlash());

                h265DecodeText.setText(isH265DecodeSupport() + "");
                h265EncodeText.setText(isH265EncodeSupport() + "");
                networkTypeText.setText(getNetworkType() + "");
            }
        });

        fullCpuInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullInfo.setText(getInfo("cpu", true));
            }
        });

        fullMemInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullInfo.setText(getInfo("mem", true));
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brand.setVisibility(View.GONE);
                manufacturer.setVisibility(View.GONE);
                product.setVisibility(View.GONE);
                model.setVisibility(View.GONE);
                device.setVisibility(View.GONE);
                board.setVisibility(View.GONE);
                hardware.setVisibility(View.GONE);
                cpuInfo.setVisibility(View.GONE);
                ram.setVisibility(View.GONE);
                rom.setVisibility(View.GONE);
                androidVersion.setVisibility(View.GONE);
                display.setVisibility(View.GONE);
                h265Decode.setVisibility(View.GONE);
                h265Encode.setVisibility(View.GONE);

                brandText.setVisibility(View.GONE);
                manufacturerText.setVisibility(View.GONE);
                productText.setVisibility(View.GONE);
                modelText.setVisibility(View.GONE);
                deviceText.setVisibility(View.GONE);
                boardText.setVisibility(View.GONE);
                androidVersionText.setVisibility(View.GONE);
                hardwareText.setVisibility(View.GONE);
                cpuInfoText.setVisibility(View.GONE);
                ramText.setVisibility(View.GONE);
                romText.setVisibility(View.GONE);
                displayText.setVisibility(View.GONE);
                h265EncodeText.setVisibility(View.GONE);
                h265DecodeText.setVisibility(View.GONE);
                networkType.setVisibility(View.GONE);
                networkTypeText.setVisibility(View.GONE);

                fullInfo.setText("");
            }
        });

    }


    public static String getInfo(String item, boolean isFull) {
        String infoPath = null;
        if (item.equals("cpu")) {
            infoPath = "/proc/cpuinfo";
        } else if (item.equals("mem")) {
            infoPath = "/proc/meminfo";
        }
        String infoText = "";
        String[] arrayOfString;
        StringBuilder info = new StringBuilder();

        try {
            FileReader fileReader = new FileReader(infoPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader, 8192);

            String tempReadLine;

            if (isFull) {
                while ((tempReadLine = bufferedReader.readLine()) != null) {
                    info.append(tempReadLine).append("\n");
                }
                return info.toString();
            }

            while ((tempReadLine = bufferedReader.readLine()) != null) {
                if (tempReadLine.startsWith("Hardware")) {
                    infoText = tempReadLine;
                    break;
                }
            }

            arrayOfString = infoText.split("\\s+");

            if (arrayOfString.length > 0) {
                for (int i = 2; i < arrayOfString.length; i++) {
                    info.append(arrayOfString[i]).append(" ");
                }
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return info.toString().trim();
    }

    public String getTotalMemory() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return Formatter.formatFileSize(getBaseContext(), memoryInfo.totalMem);
    }

    public String getTotalFlash() {
        StatFs statRootFs = new StatFs(Environment.getRootDirectory().getPath()),
                statDataFs = new StatFs(Environment.getDataDirectory().getPath());
        return Formatter.formatFileSize(getBaseContext(), statDataFs.getTotalBytes() + statRootFs.getTotalBytes());
    }

    public boolean isH265EncodeSupport() {
        int count = MediaCodecList.getCodecCount();
        for (int i = 0; i < count; i++) {
            MediaCodecInfo info = MediaCodecList.getCodecInfoAt(i);
            String name = info.getName();
            Log.d("MediaCodecInfo-name:", name);
            boolean b = info.isEncoder();
            if (b && name.contains("hevc")) {
                return true;
            }
        }
        return false;
    }

    public boolean isH265DecodeSupport() {
        int count = MediaCodecList.getCodecCount();
        for (int i = 0; i < count; i++) {
            MediaCodecInfo info = MediaCodecList.getCodecInfoAt(i);
            String name = info.getName();
            Log.d("MediaCodecInfo-name:", name);
            if (name.contains("decoder") && name.contains("hevc")) {
                return true;
            }
        }
        return false;
    }

    public int getNetworkType() {
        return NetworkState.getNetWorkConnectionType(getBaseContext());
    }
}