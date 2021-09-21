import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader  {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        String className = "Hello";
        String methodName = "hello";

        ClassLoader classLoader = new MyClassLoader();
        Class<?> myClass = classLoader.loadClass(className);

        for (Method m : myClass.getDeclaredMethods()) {
            System.out.println(myClass.getSimpleName() + "---" + m.getName());
        }

        Object instance = myClass.getDeclaredConstructor().newInstance();

        Method method = myClass.getMethod(methodName);
        method.invoke(instance);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> getClass = null;

        try (
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Hello.xlass");

        )
        {
            int length = inputStream.available();
            byte[] byteArray = new byte[length];
            inputStream.read(byteArray);
            byte[] classBytes = decode(byteArray);
            getClass= defineClass(name, classBytes, 0, classBytes.length);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return getClass;
    }

    private static byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            targetArray[i] = (byte) (255 - byteArray[i]);
        }
        return targetArray;
    }

}
