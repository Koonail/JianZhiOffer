package com.koonail.arithmetics;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: WEN KANG
 * @Description:排序
 * @Time : 2018/6/1 14:01
 */
public class PaiXu {
    static class Item{
        private Date date;
        private String name;

        public Item() {
        }

        public Item(Date date, String name) {
            this.date = date;
            this.name = name;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void sortItem(List<Item> items){
        Item temp = new Item();
        for (int i = 0;i < items.size();i++){
            for(int j = 0;j < items.size() - i - 1;j++){
                if (items.get(j).getDate().after(items.get(j + 1).getDate())){
                    temp = items.get(j);
                    items.set(j, items.get(j + 1));
                    items.set(j + 1,temp);
                } else if (items.get(j).getDate().equals(items.get(j + 1).getDate())){
                    if (items.get(j).getName().compareTo(items.get(j + 1).getName()) > 0){
                        temp = items.get(j);
                        items.set(j, items.get(j + 1));
                        items.set(j + 1,temp);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = dateFormat1.parse("2011-11-11");
            Date d2 = dateFormat1.parse("2011-11-12");
            Item item1 = new Item(d1,"BAC");
            Item item2 = new Item(d2,"ABC");
            Item item3 = new Item(d1,"AAB");
            List<Item> items = new ArrayList<Item>();
            items.add(item1);
            items.add(item2);
            items.add(item3);
            System.out.println("排序前：");
            for (Item item : items)
            {
                System.out.print(item.getName() + "  ");
            }
            sortItem(items);
            System.out.println("排序后：");
            for (Item item : items)
            {
                System.out.print(item.getName() + "  ");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
