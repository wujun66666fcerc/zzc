package com.shihui.fd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shihui.common.DTO.RecommendationRequest;
import com.shihui.common.vo.Result;
import com.shihui.fd.entity.Dish;
import com.shihui.fd.entity.UserFavoriteDish;
import com.shihui.fd.entity.UserLikeDish;
import com.shihui.fd.mapper.DishMapper;
import com.shihui.fd.service.IDishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shihui
 * @since 2024-02-15
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {
    @Autowired
    private DishMapper dishMapper;
    private UserFavoriteDishServiceImpl userFavoriteDishService;
    private UserLikeDishServiceImpl userLikeDishService;

    @Override
    public List<Dish> listTopDishes(int limit) {
        QueryWrapper<Dish> wrapper=new QueryWrapper<>();
        wrapper.last("limit "+limit);
        return baseMapper.selectList(wrapper);

    }

    @Override
    public List<Dish> getPromotedDishes() {
        // 构建查询条件，查询 promotion_status 为 1 的菜品数据列表
        QueryWrapper<Dish> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("promotion_status", 1);
        List<Dish> promotedDishes = dishMapper.selectList(queryWrapper);
        return promotedDishes;
    }
    // 区域、口味、种类、风味菜系和忌口的映射关系
    private static final Map<Integer, String> VALUE_MAP = new HashMap<>();

    static {
        // 初始化映射关系
        VALUE_MAP.put(1, "松园");
        VALUE_MAP.put(2, "菊园");
        VALUE_MAP.put(3, "荷园");
        VALUE_MAP.put(4, "柳园");
        VALUE_MAP.put(5, "商业街");
        VALUE_MAP.put(6, "酸");
        VALUE_MAP.put(7, "甜");
        VALUE_MAP.put(8, "咸");
        VALUE_MAP.put(9, "辣");
        VALUE_MAP.put(10, "清淡");
        VALUE_MAP.put(11, "米");
        VALUE_MAP.put(12, "面");
        VALUE_MAP.put(13, "汤");
        VALUE_MAP.put(14, "饼");
        VALUE_MAP.put(15, "粥");
        VALUE_MAP.put(16, "小吃");
        VALUE_MAP.put(17, "浙菜");
        VALUE_MAP.put(18, "湘菜");
        VALUE_MAP.put(19, "川菜");
        VALUE_MAP.put(20, "豫菜");
        VALUE_MAP.put(21, "粤菜");
        VALUE_MAP.put(22, "淮扬菜");
        VALUE_MAP.put(23, "外国菜");
        VALUE_MAP.put(24, "其他");
        VALUE_MAP.put(25, "猪肉");
        VALUE_MAP.put(26, "牛肉");
        VALUE_MAP.put(27, "羊肉");
        VALUE_MAP.put(28, "鱼肉");
        VALUE_MAP.put(29, "鸡肉");
        VALUE_MAP.put(30, "鸭肉");
    }
    private String mapValue(int value) {
        return VALUE_MAP.getOrDefault(value, "");
    }

    @Override
    public List<Dish> filterDishes(String conditions) {


        String[] conditionArray = conditions.split(",");

        // 构建查询条件
        QueryWrapper<Dish> queryWrapper = new QueryWrapper<>();

        // 区域条件集合
        List<String> locationConditions = new ArrayList<>();
        // 口味条件集合
        List<String> tasteConditions = new ArrayList<>();
        // 种类条件集合
        List<String> categoryConditions = new ArrayList<>();
        // 风味菜系条件集合
        List<String> cuisineConditions = new ArrayList<>();
        // 忌口条件集合
        List<String> ingredientConditions = new ArrayList<>();

        // 遍历条件，根据类型放入对应的集合中
        for (String condition : conditionArray) {
            int value = Integer.parseInt(condition);
            switch (value) {
                case 1: case 2: case 3: case 4: case 5:
                    locationConditions.add(mapValue(value));
                    break;
                case 6: case 7: case 8: case 9: case 10:
                    tasteConditions.add(mapValue(value));
                    break;
                case 11: case 12: case 13: case 14: case 15: case 16:
                    categoryConditions.add(mapValue(value));
                    break;
                case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 24:
                    cuisineConditions.add(mapValue(value));
                    break;
                case 25: case 26: case 27: case 28: case 29: case 30:
                    ingredientConditions.add(mapValue(value));
                    break;
                default:
                    // 其他情况，可以忽略或抛出异常
            }
        }
        if (!locationConditions.isEmpty()){
            queryWrapper.and(wrapper -> {
                for (String location : locationConditions) {
                    wrapper.or().like("location", location);
                }
            });
        }

        if (!tasteConditions.isEmpty()) {
            queryWrapper.and(wrapper -> {
                for (String taste : tasteConditions) {
                    wrapper.or().like("taste", taste);
                }
            });
        }

        if (!categoryConditions.isEmpty()) {
            queryWrapper.and(wrapper -> {
                for (String category : categoryConditions) {
                    wrapper.or().like("category", category);
                }
            });
        }

        if (!cuisineConditions.isEmpty()) {
            queryWrapper.and(wrapper -> {
                for (String cuisine : cuisineConditions) {
                    wrapper.or().like("cuisine", cuisine);
                }
            });
        }
        // 忌口条件
        if (!ingredientConditions.isEmpty()) {
            queryWrapper.and(wrapper -> ingredientConditions.forEach(ingredient -> wrapper.notLike("ingredients", ingredient)));
        }

        // 查询数据
        List<Dish> result = list(queryWrapper);



        return result;
    }

    @Override
    public void incrementTotalLikes(Integer dishId) {
        dishMapper.incrementTotalLikes(dishId);
    }

    @Override
    public void decrementTotalLikes(Integer dishId) {
        dishMapper.decrementTotalLikes(dishId);
    }

    @Override
    public void incrementTotalFavs(Integer dishId) {
        dishMapper.incrementTotalFavs(dishId);
    }

    @Override
    public void decrementTotalFavs(Integer dishId) {
        dishMapper.decrementTotalFavs(dishId);
    }

    @Override
    public List<Dish> getRecommendations(RecommendationRequest request) {
        Dish baseDish = getRandomDishFromUserFavoritesOrLikes(request.getAccount());
        //System.out.println(baseDish);
        List<Integer> displayDishIds = request.getDisplayedDishIds();
        List<Dish> allDishes = this.list();
        allDishes.removeIf(dish->displayDishIds.contains(dish.getDishId()));
        int minLikes = Integer.MAX_VALUE;
        int maxLikes = Integer.MIN_VALUE;
        int minFavorites = Integer.MAX_VALUE;
        int maxFavorites = Integer.MIN_VALUE;

        for (Dish dish : allDishes) {
            int likes = dish.getTotalLikes();
            int favorites = dish.getTotalFavorites();

            // 计算最小值和最大值
            minLikes = Math.min(minLikes, likes);
            maxLikes = Math.max(maxLikes, likes);
            minFavorites = Math.min(minFavorites, favorites);
            maxFavorites = Math.max(maxFavorites, favorites);
        }
        int finalMinLikes = minLikes;
        int finalMinFavorites = minFavorites;
        int finalMaxLikes = maxLikes;
        int finalMaxFavorites = maxFavorites;
        Collections.sort(allDishes,(d1, d2)->calculateSimilarity(baseDish, d2, finalMinLikes, finalMinFavorites, finalMaxLikes, finalMaxFavorites) - calculateSimilarity(baseDish, d1,finalMinLikes, finalMinFavorites, finalMaxLikes, finalMaxFavorites));
        int size = request.getSize();
        List<Dish> recommendations = allDishes.subList(0, Math.min(size, allDishes.size()));
//        for(Dish dish:recommendations){
//            System.out.println(dish+":"+calculateSimilarity(baseDish,dish,finalMinLikes, finalMinFavorites, finalMaxLikes, finalMaxFavorites));
//        }
        return recommendations;

    }

    @Override
    public List<Dish> findSimilarDishesByCategory(List<String> categories) {
        List<Dish> similarDishes = new ArrayList<>();
        for (String category : categories) {
            List<Dish> dishes = dishMapper.findSimilarDishesByName(category);
            similarDishes.addAll(dishes);
        }
        return similarDishes;
    }

    private static final double PRICE_THRESHOLD = 2.0;
    private int calculateSimilarity(Dish baseDish,  Dish otherDish,int min_likes,int min_favorites,int max_likes,int max_favorites) {
        int similarityScore = 0;

        // 比较口味
        Set<String> baseTastes = new HashSet<>(Arrays.asList(baseDish.getTaste().split(",")));
        Set<String> otherTastes = new HashSet<>(Arrays.asList(otherDish.getTaste().split(",")));
        similarityScore += calculateSetSimilarity(baseTastes, otherTastes);
        //System.out.println(baseDish.getDishName()+"和"+otherDish.getDishName()+"的口味相似度为"+similarityScore);

        // 比较菜系
        if (baseDish.getCuisine().equals(otherDish.getCuisine())) {
            similarityScore++;
           // System.out.println(baseDish.getDishName()+"和"+otherDish.getDishName()+"的菜系相同加一分");
        }

        // 比较原料
        Set<String> baseIngredients = new HashSet<>(Arrays.asList(baseDish.getIngredients().split(",")));
        Set<String> otherIngredients = new HashSet<>(Arrays.asList(otherDish.getIngredients().split(",")));
        similarityScore += calculateSetSimilarity(baseIngredients, otherIngredients);
        //System.out.println(baseDish.getDishName()+"和"+otherDish.getDishName()+"的原料相似度为"+calculateSetSimilarity(baseIngredients, otherIngredients));

        // 比较类别
        if (baseDish.getCategory().equals(otherDish.getCategory())) {
            similarityScore++;
            //System.out.println(baseDish.getDishName()+"和"+otherDish.getDishName()+"的类别相同加一分");
        }

        // 比较地理位置
        if (baseDish.getLocation().equals(otherDish.getLocation())) {
            similarityScore++;
            //System.out.println(baseDish.getDishName()+"和"+otherDish.getDishName()+"的区域相同加一分");
        }

        // 比较价格
        if (Math.abs(baseDish.getDishPrice() - otherDish.getDishPrice()) < PRICE_THRESHOLD) {
            similarityScore++;
            //System.out.println(baseDish.getDishName()+"和"+otherDish.getDishName()+"的价格阈值加一分");
        }

        // 比较地理位置
        if (baseDish.getStoreId().equals(otherDish.getStoreId())) {
            similarityScore++;
            //System.out.println(baseDish.getDishName()+"和"+otherDish.getDishName()+"的商家相同加一分");
        }
        // 计算归一化后的相似度得分
        double normalizedLikes = (double) (otherDish.getTotalLikes() - min_likes) / (max_likes - min_likes);
        double normalizedFavorites = (double) (otherDish.getTotalFavorites() - min_favorites) / (max_favorites - min_favorites);
        double s=normalizedFavorites+normalizedLikes;

        // 将权重后的相似度得分转换为整数类型，四舍五入
        similarityScore +=(int) Math.round(s);
        //System.out.println(min_likes+" "+min_favorites+" "+max_likes+" "+max_favorites);
        //System.out.println(baseDish.getDishName()+"和"+otherDish.getDishName()+"的点赞收藏加了"+(int) Math.round(s));

        return similarityScore;

    }
    private int calculateSetSimilarity(Set<String> set1, Set<String> set2) {
        int commonElements = 0;
        for (String element : set1) {
            if (set2.contains(element)) {
                commonElements++;
            }
        }
        return commonElements;
    }

    private Dish getRandomDishFromUserFavoritesOrLikes(String account) {

        List<Dish> likedDishes = dishMapper.getLikedDishesByAccount(account);
        // 从用户收藏或点赞的菜品中查询数据
        List<Dish> favoriteDishes = dishMapper.getMyFavDishes(account);


        // 合并收藏和点赞的菜品列表
        List<Dish> userDishes = new ArrayList<>();
        userDishes.addAll(favoriteDishes);
        userDishes.addAll(likedDishes);
        System.out.println(666);

        // 随机选择一道菜品
        if (!userDishes.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(userDishes.size());
            return userDishes.get(randomIndex);
        } else {
            return getTop10RatedDish();
        }
    }

    private Dish getTop10RatedDish() {
        List<Dish> top10=dishMapper.getTop10RatedDish();
        Random random = new Random();
        int randomIndex = random.nextInt(top10.size());
        return top10.get(randomIndex);
    }


}
