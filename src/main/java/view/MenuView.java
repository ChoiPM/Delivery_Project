package view;

import persistence.MyBatisConnectionFactory;
import persistence.dao.MenuManagementDAO;
import persistence.dao.StoreManagementDAO;
import persistence.dto.MenuDTO;
import persistence.dto.MenuOptionDTO;
import persistence.dto.StoreDTO;

import java.time.LocalDateTime;
import java.util.*;

public class MenuView
{
    Scanner sc = new Scanner(System.in);
    MenuManagementDAO mmd = new MenuManagementDAO(MyBatisConnectionFactory.getSqlSessionFactory());
    public void printAll()
    {
        List<MenuDTO> MDTOs = mmd.showAll();
        MDTOs.stream().forEach(p->System.out.println(p.getCategory()+" | "+p.getMenuId()+" | "+p.getName()+" | "+p.getPrice()));
    }

    public void modify_Menu()
    {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        Map params = new HashMap<String, Objects>();
        List<StoreDTO> posts;
        sb.append("menu category: ");
        List<MenuDTO> menu_post=mmd.selectAll();
        for(MenuDTO i:menu_post)
        {
            String str=i.getCategory();
            sb.append(str);
            sb.append(" ");
        }
        List<MenuDTO> category_post=mmd.selectAll();
        for(MenuDTO i:category_post)
        {
            String str=Integer.toString(i.getMenuId())+" "+i.getName()+" ";
            sb2.append(str);
            sb2.append(" ");
        }

        // System.out.println(sb);
        // System.out.println("check menu category"+sb);
        // String foodCategory = sc.nextLine();

        System.out.println("select Number: "+sb2);
        int fNum = sc.nextInt();

        System.out.println("1. modify just menu name 2. modify just menu price, 3. modify all");
        int menuNum=sc.nextInt();

        String menuName = "";
        int menuPrice=0;
        sc.nextLine();
        if(menuNum==1)
        {
            System.out.println("modify menu name");
            menuName = sc.nextLine();

            params.put("m_name",menuName);
            params.put("menuId",fNum );


            posts = mmd.modifyMenuName(params);
            posts.stream().forEach(p-> System.out.println(p.toString()));
        }
        else if(menuNum==2)
        {
            System.out.println("modify menu price");
            menuPrice = sc.nextInt();

            params.put("price", menuPrice);
            params.put("menuId",fNum );

            posts = mmd.modifyMenuPrice(params);
            posts.stream().forEach(p-> System.out.println(p.toString()));
        }
        else if(menuNum==3)
        {
            System.out.println("modify menu name");
            menuName = sc.nextLine();
            System.out.println("modify menu price");
            menuPrice = sc.nextInt();


            params.put("m_name",menuName);
            params.put("price",menuPrice);
            params.put("menuId",fNum );

            posts = mmd.modifyMenu2param(params);
            posts.stream().forEach(p-> System.out.println(p.toString()));
        }

    }
    /*public void insertOption()
    {
        System.out.println("option id");
        int o_id = sc.nextInt();
        System.out.println("option");
        String o_option =sc.nextLine();
        System.out.println("price");
        int optionprice = sc.nextInt();


        Map params = new HashMap<String, Objects>();

        params.put("menuOptionId",o_id);
        params.put("option",o_option);
        params.put("price",optionprice);

        List<MenuOptionDTO> posts = mmd.registerOption(params);
        posts.stream().forEach(p-> System.out.println(p.toString()));

    }*/
    public void registerOption()
    {
        Map params = new HashMap<String, Object>();
        String[] name = {"a", "b", "c", "d"};
        int[] price = {400, 1000, 1000, 300};


        List<MenuOptionDTO> posts = null;
        for(int i = 0; i < name.length; i++) {
            params.put("option", name[i]);
            params.put("price", price[i]);

            posts = mmd.RegisterOption(params);
        }

        posts.stream().forEach(p-> System.out.println(p.toString()));
    }

    public void registerMenu()
    {
        Map params = new HashMap<String, Object>();
        List<MenuOptionDTO> posts = null;
        String[] category = {"고기고기시리즈", "정식시리즈"};
        String[] name = {"돈까스도련님고기고기", "탕수육도련님고기고기", "새치 고기고기", "돈치 고기고기", "제육 김치찌개 정식", "제육 김치 부대찌개 정식", "돈치스팸 김치 부대찌개 정식"};
        int[] price = {6000, 5800, 6700, 5800, 8200, 8500, 8500};
        String[] option = {"1,2,3,4", "1,2,3,4", "1,2,3,4", "1,2,3,4", "1,2,3,4", "1, 2", "1, 2"};
        int[] stock = {10, 10, 10, 10, 10, 10, 1};
        for(int i = 0; i < name.length; i++)
        {
            if(i <= 3)
            {
                params.put("category", category[0]);
                params.put("m_name", name[i]);
                params.put("price", price[i]);
                //       params.put("optionNum", option[i]);
                //       params.put("stock", stock[i]);

            }
            else
            {
                params.put("category", category[1]);
                params.put("m_name", name[i]);
                params.put("price", price[i]);
                //     params.put("optionNum", option[i]);
                //     params.put("stock", stock[i]);
            }
            posts = mmd.registerMenu(params);
            //   posts.stream().forEach(p-> System.out.println(p.toString()));
        }
    }


}