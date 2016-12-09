package com.spark.network.service;

import java.util.List;

import com.spark.network.entity.RepairForm;
import com.spark.network.form.AnalyCount;

public interface IDataAnalyseService {

public  List<AnalyCount> timeAnalyseToday();

public  List<AnalyCount> timeAnalyseYesterday();

public  List<AnalyCount> timeAnalyseThisWeek();

public  List<AnalyCount> timeAnalyseThisMonth();

public  List<AnalyCount> timeAnalyseThisYear();

public  List<AnalyCount> posAnalyseToday();

public  List<AnalyCount> posAnalyseYesterday();

public  List<AnalyCount> posAnalyseThisweek();

public  List<AnalyCount> posAnalyseThisMonth();

public  List<AnalyCount> posAnalyseThisyear();



}
