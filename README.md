# DevBySimon-SimonListView
Developed By Simon!
一、如果你只是需要一个简单的下拉刷新上拉加载的功能：
1>.在xml布局中更换控件名为com.ydsimon.SimonListView

2>.在.java中实现SimonListViewListener

  重写两个方法： 
  /**
  * 刷新数据
  */

  @Override
    public void onRefresh() {
        
    }

  /**
  * 加载更多
  */

   @Override
    public void onLoadMore() {
        
    }
    
3>.控制是否刷新：mListView.setPullRefreshEnable(false);
   控制是否加载：mListView.setPullLoadEnable(false);

二、如果你需要侧滑删除功能：

1>.//创建滑动
  SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.RED));
                openItem.setWidth(dp2px(90));
                openItem.setTitle("删除");//添加文字
                //openItem.setIcon(R.drawable.ic_delete);//添加图片
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);
            }
        };
2>.//添加滑动
  mListView.setMenuCreator(creator);   
  
3>.//设置MenuItem的点击事件监听
  mListView.setOnMenuItemClickListener(new SimonListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                
                    case 0:
                        // open
                        Toast.makeText(getApplicationContext(), "DEL" + position, Toast.LENGTH_SHORT).show();
                        break;
                        
                    case 1:
                        // delete
                        Toast.makeText(getApplicationContext(), "DEL" + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        
4>.//设置滑动监听
  mListView.setOnSwipeListener(new SimonListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {

            }

            @Override
            public void onSwipeEnd(int position) {

            }
        });        
        
5>. // 其它
		mListView.setCloseInterpolator(new BounceInterpolator());   
		
三、因为是继承ListView所以Listview的其它属性保持不变，比如：setOnItemClickListener	setOnItemLongClickListener...




  @因为jcenter()的关系  AS暂不支持compile格式...
  
  @为了方便Android开发，推荐大家一个网站：www.ydsimon.net.cn
  
  
  
  
  
  
  
