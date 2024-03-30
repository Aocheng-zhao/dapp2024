package be.kuleuven.foodrestservice.controllers;

import be.kuleuven.foodrestservice.domain.*;
import be.kuleuven.foodrestservice.exceptions.MealNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class MealsRestController {

    private final MealsRepository mealsRepository;
    private final OrdersRepository ordersRepository;

    @Autowired
    MealsRestController(MealsRepository mealsRepository, OrdersRepository ordersRepository) {
        this.mealsRepository = mealsRepository;
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/rest/meals/{id}")
    EntityModel<Meal> getMealById(@PathVariable String id) {
        Meal meal = mealsRepository.findMeal(id).orElseThrow(() -> new MealNotFoundException(id));

        return mealToEntityModel(id, meal);
    }

    @GetMapping("/rest/meals")
    CollectionModel<EntityModel<Meal>> getMeals() {
        Collection<Meal> meals = mealsRepository.getAllMeal();

        List<EntityModel<Meal>> mealEntityModels = new ArrayList<>();
        for (Meal m : meals) {
            EntityModel<Meal> em = mealToEntityModel(m.getId(), m);
            mealEntityModels.add(em);
        }
        return CollectionModel.of(mealEntityModels,
                linkTo(methodOn(MealsRestController.class).getMeals()).withSelfRel());
    }

    @GetMapping("/rest/meals/cheapest")
    CollectionModel<EntityModel<Meal>> getCheapestMeals() {
        Collection<Meal> meals = mealsRepository.getCheapestMeals();

        List<EntityModel<Meal>> mealEntityModels = new ArrayList<>();
        for (Meal m : meals) {
            EntityModel<Meal> em = mealToEntityModel(m.getId(), m);
            mealEntityModels.add(em);
        }
        return CollectionModel.of(mealEntityModels,
                linkTo(methodOn(MealsRestController.class).getCheapestMeals()).withSelfRel());
    }

    @GetMapping("/rest/meals/largest")
    CollectionModel<EntityModel<Meal>> getLargestMeals() {
        Collection<Meal> meals = mealsRepository.getLargestMeals();

        List<EntityModel<Meal>> mealEntityModels = new ArrayList<>();
        for (Meal m : meals) {
            EntityModel<Meal> em = mealToEntityModel(m.getId(), m);
            mealEntityModels.add(em);
        }
        return CollectionModel.of(mealEntityModels,
                linkTo(methodOn(MealsRestController.class).getLargestMeals()).withSelfRel());
    }

    @PostMapping("/rest/meals")
    EntityModel<Meal> addMeal(@RequestBody Meal newMeal) {
        // Add the new meal to the repository (assuming you have a method to add meals)
        Meal meal = mealsRepository.addMeal(newMeal);
        if(meal==null){
            throw new MealNotFoundException(newMeal.getId());
        }

        // Return the added meal
        return mealToEntityModel(meal.getId(), meal);
    }


    @PutMapping("/rest/meals/{id}")
    EntityModel<Meal> updateMeal(@PathVariable String id, @RequestBody Meal newMeal) {
        // Add the new meal to the repository (assuming you have a method to add meals)
        Meal meal= mealsRepository.updateMeal(newMeal);
        if(meal == null){
            throw new MealNotFoundException(newMeal.getId());
        }else{
            return mealToEntityModel(meal.getId(), meal);
        }
    }

    @PostMapping("/rest/orders/addOrder")
    EntityModel<OrderConfirmation> addOrder(@RequestBody Order newOrder) {
        // Add the new meal to the repository (assuming you have a method to add meals)
        Optional<Order> order= ordersRepository.addOrder(newOrder);
        if(!order.isPresent()){

            throw new MealNotFoundException(String.valueOf(newOrder.getId()));
        }else{
            order.get().setAddress(newOrder.getAddress());
            order.get().setOrderItems(newOrder.getOrderItems());
            return EntityModel.of(new OrderConfirmation(order.get()));
        }
    }

    private EntityModel<Meal> mealToEntityModel(String id, Meal meal) {
        return EntityModel.of(meal,
                linkTo(methodOn(MealsRestController.class).getMealById(id)).withSelfRel(),
                linkTo(methodOn(MealsRestController.class).getMeals()).withRel("rest/meals"));
    }


}
