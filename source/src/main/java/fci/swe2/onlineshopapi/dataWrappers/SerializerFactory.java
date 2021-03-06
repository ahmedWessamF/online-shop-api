package fci.swe2.onlineshopapi.dataWrappers;

import java.util.HashMap;
import java.util.Map;

import fci.swe2.onlineshopapi.Admin;
import fci.swe2.onlineshopapi.Customer;
import fci.swe2.onlineshopapi.StoreOwner;
import fci.swe2.onlineshopapi.exceptions.UserFriendlyError;

public class SerializerFactory{
  public enum Type{
    JSON, XML
  }

  public static class MapKey {
    private Object object;
    private Type type;

    MapKey(Object o, Type t){
      this.object = o;
      this.type = t;
    }

    @Override
    public boolean equals(Object obj){
        if(obj != null && obj instanceof MapKey){
            MapKey other = (MapKey) obj;
            return other.object == object && other.type == type;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return object.hashCode() + type.hashCode();
    }

  }

  private static Map<MapKey, Object> map = new HashMap<>();

  public static void init(){
      register(Customer.class, Type.JSON, new JsonCustomerSerializer());
      register(Admin.class, Type.JSON, new JsonAdminSerializer());
      register(StoreOwner.class, Type.JSON, new JsonStoreOwnerSerializer());
      register(UserFriendlyError.class, Type.JSON, new JsonUserMessageSerializer());
      register(String.class, Type.JSON, new JsonCustomMessageSerializer());
      register(AllUsersWrapper.class, Type.JSON, new JsonAllUsersSerializer());
      register(LoginRequestWrapper.class,Type.JSON, new LoginRequestSerializer());
  }

  static{
      init();
  }

  public static <T>  void register(Class<T> c, Type t, Serializer<T> s){
      map.put(new MapKey(c, t), s);
  }

  @SuppressWarnings("unchecked")
  public static <T> Serializer<T> getSerializer(Class<T> c, Type t){
    return (Serializer<T>) map.get(new MapKey(c, t));
  }

}


