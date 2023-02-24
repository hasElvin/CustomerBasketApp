package CustomerBasketApp;

import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();
    public static void main(String[] args) {

        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.5, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.5, 200);
        stockList.addStock(temp);
        temp = new StockItem("cup", 0.45, 7);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        for(String s : stockList.Items().keySet()) {
            System.out.println(s);
        }

        Basket elvinsBasket = new Basket("Elvin");

        sellItem(elvinsBasket, "car", 1);
        System.out.println(elvinsBasket);
        
        sellItem(elvinsBasket, "car", 1);
        System.out.println(elvinsBasket);

        sellItem(elvinsBasket, "car", 1);
        sellItem(elvinsBasket, "spanner", 5);
        sellItem(elvinsBasket, "juice", 4);
        sellItem(elvinsBasket, "cup", 12);
        sellItem(elvinsBasket, "bread", 1);

        Basket basket = new Basket("Customer");
        sellItem(basket,"cup", 100);
        sellItem(basket, "juice", 5);
        removeItem(basket, "cup",1);
        System.out.println(basket);

        removeItem(elvinsBasket, "car", 1);
        removeItem(elvinsBasket, "cup", 9);
        removeItem(elvinsBasket, "car", 1);
        System.out.println("cars removed: " + removeItem(elvinsBasket, "car", 1));

        System.out.println(elvinsBasket);

        removeItem(elvinsBasket, "bread", 1);
        removeItem(elvinsBasket, "cup", 3);
        removeItem(elvinsBasket, "juice", 4);
        removeItem(elvinsBasket, "cup", 3);
        System.out.println(elvinsBasket);

        System.out.println("\nDisplay stock list and checkout");
        System.out.println(elvinsBasket);
        System.out.println(stockList);
        checkOut(basket);
        System.out.println(basket);
        System.out.println(stockList);

        checkOut(elvinsBasket);
        System.out.println(elvinsBasket);
    }

    public static int sellItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(stockList.reserveStock(item, quantity) != 0) {
            return basket.addToBasket(stockItem, quantity);
        }
        return 0;
    }

    public static int removeItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(basket.removeFromBasket(stockItem, quantity) == quantity) {
            return stockList.unreserveStock(item, quantity);
        }
        return 0;
    }

    public static void checkOut(Basket basket) {
        for(Map.Entry<StockItem, Integer> item : basket.Items().entrySet()) {
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }
}
