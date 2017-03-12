package com.edu.zzti.ass.management.controller;

import java.awt.BasicStroke;  
import java.awt.Color;  
import java.awt.Font;  
import java.io.IOException;  
import java.io.PrintWriter;  
import java.io.Writer;  
import java.text.DecimalFormat;  
import java.text.NumberFormat;  
import java.util.List;
import java.util.Map;
  
  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartRenderingInfo;  
import org.jfree.chart.ChartUtilities;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.AxisLocation;  
import org.jfree.chart.axis.CategoryAxis;  
import org.jfree.chart.axis.NumberAxis;  
import org.jfree.chart.axis.ValueAxis;  
import org.jfree.chart.entity.StandardEntityCollection;  
import org.jfree.chart.labels.IntervalXYItemLabelGenerator;  
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;  
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;  
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;  
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;  
import org.jfree.chart.labels.StandardPieToolTipGenerator;  
import org.jfree.chart.plot.CategoryPlot;  
import org.jfree.chart.plot.PiePlot;  
import org.jfree.chart.plot.PiePlot3D;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.chart.plot.XYPlot;  
import org.jfree.chart.renderer.category.BarRenderer;  
import org.jfree.chart.renderer.category.BarRenderer3D;  
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;  
import org.jfree.chart.servlet.ServletUtilities;  
import org.jfree.chart.title.TextTitle;  
import org.jfree.chart.urls.StandardCategoryURLGenerator;  
import org.jfree.chart.urls.StandardPieURLGenerator;  
import org.jfree.chart.urls.StandardXYURLGenerator;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;  
import org.jfree.data.general.Dataset;  
import org.jfree.data.general.DefaultPieDataset;  
import org.jfree.data.xy.XYDataset;  
import org.jfree.data.xy.XYSeries;  
import org.jfree.data.xy.XYSeriesCollection;  
import org.jfree.ui.RectangleInsets;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.servlet.ModelAndView;  

import com.edu.zzti.ass.management.service.IRecordService;
  
@Controller  
@RequestMapping("/pages/Jfree")  
public class JfreeController {  
    
	@Autowired
	private IRecordService recordService;
	
    @RequestMapping("/showXYLine")  
    public ModelAndView showXYLine(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");  
        HttpSession session = request.getSession();  
        Writer out = response.getWriter();  
  
        String filename = createXYLineChart(session,"统计图","X轴","Y轴",550, 450, "imgMap", new PrintWriter(out));  
              
        String file = request.getContextPath()  
                + "/servlet/DisplayChart?filename=" + filename;  
        ModelAndView mav = new ModelAndView();  
        mav.addObject("file", file);  
        mav.setViewName("/jfree");  
        return mav;  
    }  
      
    @RequestMapping("/showBar3D")  
    public ModelAndView showBar3D(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");  
        HttpSession session = request.getSession();  
        Writer out = response.getWriter();  
  
        String filename = createBarChart(getCategoryBarDataset(), "招生信息总览",  
                "应报与实报对照", "人数", 550, 450, session, new PrintWriter(out),  
                "imgMap", true);  
        String file = request.getContextPath()  
                + "/servlet/DisplayChart?filename=" + filename;  
        ModelAndView mav = new ModelAndView();  
        mav.addObject("file", file);  
        mav.setViewName("/jfree");  
        return mav;  
    }  
  
    @RequestMapping("/detailBar3D")  
    public ModelAndView detailBar3D(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
  
        String type = request.getParameter("type");  
        type = new String(type.getBytes("ISO-8859-1"), "UTF-8");  
        String school = request.getParameter("school");  
        school = new String(school.getBytes("ISO-8859-1"), "UTF-8");  
  
        CategoryDataset data = getCategoryBar3DDataset(type, school);  
  
        HttpSession session = request.getSession();  
        Writer out = response.getWriter();  
  
        String filename = createBarChart(data, school + type + "男女情况3D图",  
                "男女人数", "人数", 550, 450, session, new PrintWriter(out),  
                "imgMap", true);  
  
        String file = request.getContextPath()  
                + "/servlet/DisplayChart?filename=" + filename;  
        ModelAndView mav = new ModelAndView();  
        mav.addObject("file", file);  
        mav.setViewName("/jfree");  
        return mav;  
    }  
    @RequestMapping("/showBar")  
    public ModelAndView showBar(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");  
        HttpSession session = request.getSession();  
        Writer out = response.getWriter();  
  
        String filename = createBarChart(getCategoryBarDataset(), "招生信息总览",  
                "应报与实报对照", "人数", 550, 450, session, new PrintWriter(out),  
                "imgMap", false);  
        String file = request.getContextPath()  
                + "/servlet/DisplayChart?filename=" + filename;  
        ModelAndView mav = new ModelAndView();  
        mav.addObject("file", file);  
        mav.setViewName("/jfree");  
        return mav;  
    }  
  
    @RequestMapping("/detailBar")  
    public ModelAndView detailBar(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
  
        String type = request.getParameter("type");  
        type = new String(type.getBytes("ISO-8859-1"), "UTF-8");  
        String school = request.getParameter("school");  
        school = new String(school.getBytes("ISO-8859-1"), "UTF-8");  
  
        CategoryDataset data = getCategoryBar3DDataset(type, school);  
        HttpSession session = request.getSession();  
        Writer out = response.getWriter();  
  
        String filename = createBarChart(data, school + type + "男女情况平面图",  
                "男女人数", "人数", 550, 450, session, new PrintWriter(out),  
                "imgMap", false);  
  
        String file = request.getContextPath()  
                + "/servlet/DisplayChart?filename=" + filename;  
        ModelAndView mav = new ModelAndView();  
        mav.addObject("file", file);  
        mav.setViewName("/jfree");  
        return mav;  
    }  
  
    @RequestMapping("/showPie3D")  
    public ModelAndView showPie3D(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");  
        HttpSession session = request.getSession();  
        Writer out = response.getWriter();  
  
        String filename = createPieChart(getPieDataset(), "各月份系统访问人数占有率统计", 550, 400,  
                session, new PrintWriter(out), "imgMap", true);  
        String file = request.getContextPath()  
                + "/servlet/DisplayChart?filename=" + filename;  
        ModelAndView mav = new ModelAndView();  
        mav.addObject("file", file);  
        mav.setViewName("/jfree");  
        return mav;  
    }  
  
    @RequestMapping("/detailPie3D")  
    public ModelAndView detailPie3D(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
  
        String fruit = request.getParameter("fruit");  
        fruit = new String(fruit.getBytes("ISO-8859-1"), "UTF-8");  
  
        DefaultPieDataset data = getDetailPieDataSet(fruit);  
        HttpSession session = request.getSession();  
        Writer out = response.getWriter();  
  
        String filename = createPieChart(data, "全国" + fruit + "产量图", 550, 400,  
                session, new PrintWriter(out), "imgMap", true);  
        String file = request.getContextPath()  
                + "/servlet/DisplayChart?filename=" + filename;  
        ModelAndView mav = new ModelAndView();  
        mav.addObject("file", file);  
        mav.setViewName("/jfree");  
  
        return mav;  
    }  
  
    @RequestMapping("/showPie")  
    public ModelAndView showPie(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");  
        HttpSession session = request.getSession();  
        Writer out = response.getWriter();  
  
        String filename = createPieChart(getPieDataset(), "全国水果产量图", 550, 400,  
                session, new PrintWriter(out), "imgMap", false);  
        String file = request.getContextPath()  
                + "/servlet/DisplayChart?filename=" + filename;  
        ModelAndView mav = new ModelAndView();  
        mav.addObject("file", file);  
        mav.setViewName("/jfree");  
        return mav;  
    }  
  
    @RequestMapping("/detailPie")  
    public ModelAndView detailPie(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
        response.setContentType("text/html;charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
  
        String fruit = request.getParameter("fruit");  
        fruit = new String(fruit.getBytes("ISO-8859-1"), "UTF-8");  
  
        DefaultPieDataset data = getDetailPieDataSet(fruit);  
        HttpSession session = request.getSession();  
        Writer out = response.getWriter();  
  
        String filename = createPieChart(data, "全国" + fruit + "产量图", 550, 400,  
                session, new PrintWriter(out), "imgMap", false);  
        String file = request.getContextPath()  
                + "/servlet/DisplayChart?filename=" + filename;  
        ModelAndView mav = new ModelAndView();  
        mav.addObject("file", file);  
        mav.setViewName("/jfree");  
  
        return mav;  
    }  
  
    public DefaultPieDataset getDetailPieDataSet(String fruit) {  
        DefaultPieDataset data = new DefaultPieDataset();  
        if ("苹果".equals(fruit)) {  
            data.setValue("山东", 100);  
            data.setValue("广东", 200);  
            data.setValue("湖南", 300);  
            data.setValue("湖北", 150);  
            data.setValue("四川", 150);  
            data.setValue("重庆", 50);  
            data.setValue("云南", 250);  
        }  
        if ("梨子".equals(fruit)) {  
            data.setValue("山东", 150);  
            data.setValue("广东", 250);  
            data.setValue("湖南", 350);  
            data.setValue("湖北", 150);  
            data.setValue("四川", 450);  
            data.setValue("重庆", 100);  
            data.setValue("云南", 550);  
        }  
        if ("葡萄".equals(fruit)) {  
            data.setValue("山东", 150);  
            data.setValue("广东", 250);  
            data.setValue("湖南", 650);  
            data.setValue("湖北", 150);  
            data.setValue("四川", 750);  
            data.setValue("重庆", 500);  
            data.setValue("云南", 550);  
        }  
        if ("香蕉".equals(fruit)) {  
            data.setValue("山东", 150);  
            data.setValue("广东", 250);  
            data.setValue("湖南", 650);  
            data.setValue("湖北", 150);  
            data.setValue("四川", 750);  
            data.setValue("重庆", 1500);  
            data.setValue("云南", 550);  
        } else {  
            data.setValue("山东", 150);  
            data.setValue("广东", 250);  
            data.setValue("湖南", 650);  
            data.setValue("湖北", 150);  
            data.setValue("四川", 750);  
            data.setValue("重庆", 1500);  
            data.setValue("云南", 1550);  
        }  
        return data;  
    }  
  
    public DefaultPieDataset getPieDataset() {  
    	List<Map> map= recordService.findBySql("SELECT COUNT(*) as sl,SUBSTR(t.operTimes,1,7) ,t.operTimes as sj FROM t_student_log t  GROUP BY SUBSTR(t.operTimes,1,7)");
        DefaultPieDataset pieDataset = new DefaultPieDataset();  
        for(int i=0;i<map.size();i++){
        	pieDataset.setValue(map.get(i).get("sj").toString().substring(0, 7),Integer.parseInt(map.get(i).get("sl").toString()));
        }
        return pieDataset;  
    }  
  
    public CategoryDataset getCategoryBar3DDataset(String type, String school) {  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
        if ("计划".equals(type)) {  
            if ("清华大学".equals(school)) {  
                dataset.addValue(150, "男", "清华大学");  
                dataset.addValue(50, "女", "清华大学");  
            } else if ("天津大学".equals(school)) {  
                dataset.addValue(60, "男", "天津大学");  
                dataset.addValue(40, "女", "天津大学");  
            } else {  
                dataset.addValue(130, "男", "郑州大学");  
                dataset.addValue(70, "女", "郑州大学");  
            }  
        }  
        if ("实报".equals(type)) {  
            if ("清华大学".equals(school)) {  
                dataset.addValue(150, "男", "清华大学");  
                dataset.addValue(250, "女", "清华大学");  
            } else if ("天津大学".equals(school)) {  
                dataset.addValue(120, "男", "天津大学");  
                dataset.addValue(85, "女", "天津大学");  
            } else {  
                dataset.addValue(170, "男", "郑州大学");  
                dataset.addValue(115, "女", "郑州大学");  
            }  
        }  
        return dataset;  
    }  
  
    public CategoryDataset getCategoryBarDataset() {  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
        dataset.addValue(200, "计划", "清华大学");  
        dataset.addValue(400, "实报", "清华大学");  
        dataset.addValue(100, "计划", "天津大学");  
        dataset.addValue(205, "实报", "天津大学");  
        dataset.addValue(200, "计划", "郑州大学");  
        dataset.addValue(285, "实报", "郑州大学");  
        return dataset;  
    }  
    private  XYDataset getXYDataset() {  
          XYSeries xyseries = new XYSeries("第一条线"); // 先产生XYSeries 对象  
          xyseries.add(1.0D, 1.0D);  
          xyseries.add(2D, 4D);  
          xyseries.add(3D, 3D);  
          xyseries.add(4D, 5D);  
          xyseries.add(5D, 5D);  
          xyseries.add(6D, 7D);  
          xyseries.add(7D, 7D);  
          xyseries.add(8D, 8D);  
  
          XYSeries xyseries1 = new XYSeries("第二条线");  
          xyseries1.add(1.0D, 5D);  
          xyseries1.add(2D, 7D);  
          xyseries1.add(3D, 6D);  
          xyseries1.add(4D, 8D);  
          xyseries1.add(5D, 4D);  
          xyseries1.add(6D, 4D);  
          xyseries1.add(7D, 2D);  
          xyseries1.add(8D, 1.0D);  
  
          XYSeries xyseries2 = new XYSeries("第三条线");  
          xyseries2.add(3D, 4D);  
          xyseries2.add(4D, 3D);  
          xyseries2.add(5D, 2D);  
          xyseries2.add(6D, 3D);  
          xyseries2.add(7D, 6D);  
          xyseries2.add(8D, 3D);  
          xyseries2.add(9D, 4D);  
          xyseries2.add(10D, 3D);  
  
          XYSeriesCollection xyseriescollection = new XYSeriesCollection(); // 再用XYSeriesCollection添加入XYSeries  
                           // 对象  
          xyseriescollection.addSeries(xyseries);  
          xyseriescollection.addSeries(xyseries1);  
          xyseriescollection.addSeries(xyseries2);  
          return xyseriescollection;  
         }  
  
    public  String createXYLineChart(HttpSession session,String title,String xtitle,String ytitle,int width,int height,String useMap,PrintWriter pw) {  
          XYDataset xydataset = getXYDataset();  
          String filename = "";  
            
          JFreeChart jfreechart = ChartFactory.createXYLineChart(  
            title,    //图表标题名称  
           xtitle,    //横坐标显示标签  
            ytitle,   //纵坐标显示标签  
            xydataset,  //数据源  
            PlotOrientation.VERTICAL, //PlotOrientation.VERTICAL-->垂直显示、PlotOrientation.HORIZONTAL-->水平显示  
            true,    //是否显示图例（对于简单的柱状图必须是false）  
            true,    //是否生产工具  
            true   //是否生成URL链接  
          );  
          jfreechart.setTitle(new TextTitle(title,new Font("黑体",Font.ITALIC,20)));//重新设置图表标题，改变字体  
  
          jfreechart.getTitle().setFont(new Font("黑体",Font.ITALIC,20)); //解决标题乱码   
            
          jfreechart.setBackgroundPaint(Color.white); //设定背景色为白色  
            
          XYPlot xyplot = (XYPlot) jfreechart.getPlot(); // 获得 plot：XYPlot！！  
            
          xyplot.setBackgroundPaint(Color.lightGray); //设定图表数据显示部分背景色  
            
          xyplot.setDomainGridlinePaint(Color.white); //横坐标网格线白色  
            
          xyplot.setDomainGridlinesVisible(true); //网格线:true-->可见,false-->不可见  
            
          xyplot.setRangeGridlinePaint(Color.white); //纵坐标网格线白色  
            
          xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D)); // 设定坐标轴与图表数据显示部分距离  
            
          ValueAxis categoryAxis = xyplot.getDomainAxis();//取得横轴  
            
        //categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//设置分类标签以45度角倾斜  
            
          categoryAxis.setLabelFont(new Font("宋体",Font.TYPE1_FONT,16));//设置横轴显示标签的字体  
  
          categoryAxis.setTickLabelFont(new Font("宋体", Font.TYPE1_FONT, 16));     //解决横坐标乱码  
  
          NumberAxis numberAxis = (NumberAxis)xyplot.getRangeAxis();//取得纵轴  
            
          numberAxis.setLabelFont(new Font("宋体",Font.TYPE1_FONT,16));//设置纵轴显示标签的字体  
            
          numberAxis.setTickLabelFont(new Font("宋体", Font.TYPE1_FONT, 16));     //解决纵坐标乱码  
            
          numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());//使纵坐标的最小单位格为整数  
            
          numberAxis.setAutoRangeIncludesZero(true);  
            
          // 获得 renderer 注意这里是XYLineAndShapeRenderer ！！  
          XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot  
            .getRenderer();  
          xylineandshaperenderer.setShapesVisible(true); // 数据点可见  
          xylineandshaperenderer.setShapesFilled(true); // 数据点被填充即不是空心点  
            
          xylineandshaperenderer.setSeriesLinesVisible(0, false); //第一个XYSeries数据点间连线不可见  
          xylineandshaperenderer.setSeriesShapesVisible(1, false); //第二个XYSeries数据点不可见  
          xyplot.setRenderer(xylineandshaperenderer);  
  
          xylineandshaperenderer.setSeriesStroke(0, new BasicStroke(2.0F, 1, 1, 1.0F,   
          new float[] {10F, 6F}, 0.0F)); //定义series为”First”的（即series1）点之间的连线 ，这里是虚线，默认是直线  
          xylineandshaperenderer.setSeriesStroke(1, new BasicStroke(2.0F, 1, 1, 1.0F,   
          new float[] {6F, 6F}, 0.0F)); //定义series为”Second”的（即series2）点之间的连线  
          xylineandshaperenderer.setSeriesStroke(2, new BasicStroke(2.0F, 1, 1, 1.0F,   
          new float[] {2.0F, 6F}, 0.0F)); //定义series为”Third”的（即series3）点之间的连线  
           
          xylineandshaperenderer.setBaseItemLabelGenerator(new IntervalXYItemLabelGenerator("({1},{2})",NumberFormat.getNumberInstance(),NumberFormat.getNumberInstance()));  
          xylineandshaperenderer.setURLGenerator(new StandardXYURLGenerator("showXYLine.do","seriesName","itemName"));  
          
          //设置底部标题字体  
          xylineandshaperenderer.setLegendTextFont(0, new Font("宋体",Font.TYPE1_FONT,16));  
          xylineandshaperenderer.setLegendTextFont(1, new Font("宋体",Font.TYPE1_FONT,16));  
          xylineandshaperenderer.setLegendTextFont(2, new Font("宋体",Font.TYPE1_FONT,16));  
          
          StandardEntityCollection sec = new StandardEntityCollection();  
          ChartRenderingInfo info = new ChartRenderingInfo(sec);  
  
            try {  
                filename = ServletUtilities.saveChartAsPNG(jfreechart, width, height,  
                        info, session);  
                ChartUtilities.writeImageMap(pw, useMap, info, false);// 输出MAP信息  
                pw.flush();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            return filename;  
    }  
  
    public String createBarChart(CategoryDataset dataset, String title,  
            String xtitle, String ytitle, int width, int height,  
            HttpSession session, PrintWriter pw, String useMap, boolean is3D) {  
        String filename = "";  
        JFreeChart chart = ChartFactory.createBarChart3D(title,// 图表标题  
                xtitle,// 目录轴的显示标签(x轴)  
                ytitle,// 数值轴的显示标签（y轴）  
                dataset, PlotOrientation.VERTICAL,// 图表方向：水平、垂直  
                true,// 是否显示图例(对于简单的柱状图必须是false)  
                true,// 是否tooltip  
                true// 是否生成URL链接  
                );  
        Font font = new Font("宋体", Font.BOLD, 16);  
        TextTitle barTitle = new TextTitle(title, font);  
        // 设置正上方的标题  
        chart.setTitle(barTitle);  
        // 设置底部标题  
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));  
        //chart.getLegend().setItemLabelPadding(new RectangleInsets());  
        CategoryPlot plot = chart.getCategoryPlot();// 获得图表区域对象  
  
        // 设置图表的纵轴和横轴org.jfree.chart.axis.CategoryAxis  
        CategoryAxis domainAxis = plot.getDomainAxis();// X轴  
        domainAxis.setLowerMargin(0.1);// 设置距离图片左端距离此时为10%  
        domainAxis.setUpperMargin(0.1);// 设置距离图片右端距离此时为百分之10  
        domainAxis.setCategoryLabelPositionOffset(10);// 图表横轴与标签的距离(10像素)  
        domainAxis.setCategoryMargin(0.2);// 横轴标签之间的距离20%  
        // 设置X坐标上的文字  
        domainAxis.setTickLabelFont(font);  
        // 设置X轴标题  
        domainAxis.setLabelFont(font);  
        // domainAxis.setMaximumCategoryLabelLines(1);  
        // domainAxis.setMaximumCategoryLabelWidthRatio(0);  
        // 设定柱子的属性  
        ValueAxis rangeAxis = plot.getRangeAxis();  
        rangeAxis.setUpperMargin(0.1);// 设置最高的一个柱与图片顶端的距离(最高柱的10%)  
        // 设置Y坐标上的文字  
        rangeAxis.setTickLabelFont(font);  
        // 设置Y轴标题  
        rangeAxis.setLabelFont(font);  
  
        // 设置图表的颜色  
        // BarRenderer3D renderer = new BarRenderer3D();  
        BarRenderer renderer;  
        if (is3D) {  
            renderer = new BarRenderer3D();  
            renderer  
                    .setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());  
            renderer.setBaseItemURLGenerator(new StandardCategoryURLGenerator(  
                    "detailBar3D.do", "type", "school"));  
        } else {  
            renderer = new BarRenderer();  
            renderer  
                    .setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());  
            renderer.setBaseItemURLGenerator(new StandardCategoryURLGenerator(  
                    "detailBar.do", "type", "school"));  
        }  
        // 显示每个柱的数值，并修改该数值的字体属性  
        renderer.setIncludeBaseInRange(true);  
        renderer  
                .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator(  
                        "{0}={2}人",NumberFormat.getNumberInstance()));  
        renderer.setBaseOutlinePaint(Color.red);  
        renderer.setSeriesPaint(0, new Color(0, 255, 255));// 计划柱子的颜色为青色  
        renderer.setSeriesOutlinePaint(0, Color.BLACK);// 边框为黑色  
        renderer.setSeriesPaint(1, new Color(0, 255, 0));// 实报柱子的颜色为绿色  
        renderer.setSeriesOutlinePaint(1, Color.red);// 边框为红色  
        renderer.setItemMargin(0.1);// 组内柱子间隔为组宽的10%  
        //java.lang.String labelFormat:"{0}={1}({2})":会显示成：apple=120(5%)   
        //java.text.NumberFormat类有三个方法可以产生下列数据的标准格式化器：  
        //数字：NumberFormat.getNumberInstance();  
        //货币：NumberFormat.getCurrencyInstance();  
        //百分数：NumberFormat.getPercentInstance();  
          
        //设置底部  
        renderer.setLegendItemLabelGenerator(new StandardCategorySeriesLabelGenerator("{0}"));  
        renderer.setBaseItemLabelsVisible(true);  
        plot.setRenderer(renderer);// 使用我们设计的效果  
          
        // 设置纵横坐标的显示位置  
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);// 学校显示在下端(柱子竖直)或左侧(柱子水平)  
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT); // 人数显示在下端(柱子水平)或左侧(柱子竖直)  
        StandardEntityCollection sec = new StandardEntityCollection();  
        ChartRenderingInfo info = new ChartRenderingInfo(sec);  
        try {  
            filename = ServletUtilities.saveChartAsPNG(chart, width, height,  
                    info, session);  
            ChartUtilities.writeImageMap(pw, useMap, info, false);// 输出MAP信息  
            pw.flush();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return filename;  
    }  
  
    public String createPieChart(Dataset dataset, String title, int width,  
            int height, HttpSession session, PrintWriter pw, String useMap,  
            boolean is3D) {  
        String filename = "";  
        PiePlot plot = null;  
        if (is3D) {  
            plot = new PiePlot3D((DefaultPieDataset) dataset);// 3D饼图  
            plot.setURLGenerator(new StandardPieURLGenerator("detailPie3D.do",  
                    "fruit", "pieIdex"));// 设定热区超链接  
        } else {  
            plot = new PiePlot((DefaultPieDataset) dataset);  
            plot.setURLGenerator(new StandardPieURLGenerator("detailPie.do",  
                    "fruit", "pieIdex"));// 设定热区超链接  
        }  
        plot.setPieIndex(1);  
        JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT,  
                plot, true);  
        chart.setBackgroundPaint(java.awt.Color.white);// 可选，设置图片背景色  
        chart.setTitle(title);// 可选，设置图片标题  
  
        // 指定饼图轮廓线的颜色  
        // plot.setBaseSectionOutlinePaint(Color.BLACK);  
        // plot.setBaseSectionPaint(Color.BLACK);  
        plot.setToolTipGenerator(new StandardPieToolTipGenerator());  
        plot.setNoDataMessage("无对应数据显示");  
  
        // 图片中显示百分比:默认方式  
        // plot.setLabelGenerator(new  
        // StandardPieSectionLabelGenerator(StandardPieToolTipGenerator.DEFAULT_TOOLTIP_FORMAT));  
        // 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位  
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(  
                "{0}={1}({2})", NumberFormat.getNumberInstance(),  
                new DecimalFormat("0.00%")));  
        // 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例  
        plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(  
                "{0}={1}({2})"));  
        // 设置背景色为白色  
        chart.setBackgroundPaint(Color.white);  
        // 指定图片的透明度(0.0-1.0)  
        plot.setForegroundAlpha(0.8f);  
        // 指定显示的饼图上圆形(true)还椭圆形(false)  
        plot.setCircular(true);  
        // 设置第一个 饼块section 的开始位置，默认是12点钟方向  
        plot.setStartAngle(90);  
        plot.setLabelFont(new Font("宋体", Font.BOLD, 12));  
        chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 12));  
        StandardEntityCollection sec = new StandardEntityCollection();  
        ChartRenderingInfo info = new ChartRenderingInfo(sec);  
        try {  
            filename = ServletUtilities.saveChartAsPNG(chart, 500, 300, info,  
                    session);  
            ChartUtilities.writeImageMap(pw, useMap, info, false);// 输出MAP信息  
            pw.flush();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return filename;  
    }  
  
} 
