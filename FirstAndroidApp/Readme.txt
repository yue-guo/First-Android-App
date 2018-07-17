1. Navigation menu: I use the android studio template to do that.

2. Legislators Section: It is showed in main activity and to achieve the 3 tabs on the top, I used the tab layout and view pager for Legislators Section, it contains a PagerAdapter(MyFirstFragmentPagerAfapter) and 3 fragment for ‘by state’, ‘house’ and ‘senate’ and for each of these 3 things, they have a adapter.

3. Bill Section: It has the same framework with the Legislators. 2 fragment and 2 adaptor and 1 pager adapter for all.

4.Committees Section: 3 fragment and 3 adaptor and 1 pager adapter.

5.DisplayDetail: 3 activity for detail display, corresponding to the legislators, bills and committees.

6.The LegisBean for Legislator and BillBean for bills and CommBean for Committees.

7.The ImageLoader is to show the image of legislator.

8.Asynctask: this is to get the son data from the API.

9.The XML file is correspond to the activity and fragment, similar name. 