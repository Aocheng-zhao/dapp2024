package be.kuleuven.foodrestservice.controllers;

import be.kuleuven.foodrestservice.domain.*;
import be.kuleuven.foodrestservice.exceptions.MealNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class MealsRestRpcStyleController {

    private final MealsRepository mealsRepository;
    private final OrdersRepository ordersRepository;

    @Autowired
    MealsRestRpcStyleController(MealsRepository mealsRepository, OrdersRepository ordersRepository) {
        this.mealsRepository = mealsRepository;
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/restrpc/meals/{id}")
    Meal getMealById(@PathVariable String id) {
        Optional<Meal> meal = mealsRepository.findMeal(id);

        return meal.orElseThrow(() -> new MealNotFoundException(id));
    }

    @GetMapping("/restrpc/meals")
    Collection<Meal> getMeals() {
        return mealsRepository.getAllMeal();
    }

    @GetMapping("/restrpc/meals/cheapest")
    Collection<Meal> getCheapestMeals() {
        return mealsRepository.getCheapestMeals();
    }

    @GetMapping("/restrpc/meals/largest")
    Collection<Meal> getLargestMeals() {
        return mealsRepository.getLargestMeals();
    }

    @PostMapping("/restrpc/meals")
    Meal addMeal(@RequestBody Meal newMeal) {
        // Add the new meal to the repository (assuming you have a method to add meals)
        Meal meal = mealsRepository.addMeal(newMeal);
        if(meal == null){
            throw new MealNotFoundException(newMeal.getId());
        }

        // Return the added meal
        return meal;
    }

    @PostMapping("/restrpc/meals/{id}")
    Meal updateMeal(@PathVariable String id, @RequestBody Meal newMeal) {
        // Add the new meal to the repository (assuming you have a method to add meals)
        Meal meal= mealsRepository.updateMeal(newMeal);
        if(meal == null){
            throw new MealNotFoundException(newMeal.getId());
        }else{
            return meal;
        }
    }

    @PostMapping("/restrpc/orders/addOrder")
    OrderConfirmation addOrder(@RequestBody Order newOrder) {
        // Add the new meal to the repository (assuming you have a method to add meals)
        Optional<Order> order= ordersRepository.addOrder(newOrder);
        if(!order.isPresent()){

            throw new MealNotFoundException(String.valueOf(newOrder.getId()));
        }else{
            order.get().setAddress(newOrder.getAddress());
            order.get().setOrderItems(newOrder.getOrderItems());
            return new OrderConfirmation(order.get());
        }
    }

}
