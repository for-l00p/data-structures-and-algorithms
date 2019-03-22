
import java.lang.reflect.Field;

class ToStringTest {

  private static int TEST_INT = 5;
  private String c;

  public ToStringTest(){
    c = "test";
  }

    @Override
  public String toString() {
      StringBuilder result = new StringBuilder();
      String newLine = System.getProperty("line.separator");

      result.append( this.getClass().getName() );
      result.append( " Object {" );
      result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
      Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
      for ( Field field : fields  ) {
        result.append("  ");
        try {
          result.append( field.getName() );
          result.append(": ");
          //requires access to private field:
          result.append( field.get(this) );
        } catch ( IllegalAccessException ex ) {
          System.out.println(ex);
        }
        result.append(newLine);
      }
      result.append("}");

      return result.toString();
  }

// From: https://stackoverflow.com/questions/52917654/why-is-recursive-mergesort-faster-than-iterative-mergesort
  public static void plot(){
    for (int i = 0; i < nbSteps; i++) {
    int N = startingCount + countIncrement * i;
    for (ISortingAlgorithm<Integer> algo : algorithms) {

        long time = 0;
        for (int j = 0; j < folds; j++) {
            ArrayToSort<Integer> toSort = new ArrayToSort<>(
                    ArrayToSort.CreateRandomIntegerArray(N, Integer.MAX_VALUE, (int) System.nanoTime())
            );
            long startTime = System.currentTimeMillis();
            algo.Sort(toSort);
            long endTime = System.currentTimeMillis();
            time += (endTime - startTime);
            assert toSort.isSorted();
        }
        stringBuilder.append(N + ", " + (time / folds) + ", " + algo.Name() + "\n");
        System.out.println(N + ", " + (time / folds) + ", " + algo.Name());
    }

}
  }

  public static void main(String[] args){

      ToStringTest t = new ToStringTest();
      System.out.println(t.toString());

  }

}

