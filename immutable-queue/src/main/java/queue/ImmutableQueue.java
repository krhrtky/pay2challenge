package queue;

public class ImmutableQueue<T> implements Queue<T> {

    private Data<T> data;

    public ImmutableQueue(T data) {
        this.data = new Data<>(data);
    }

    private ImmutableQueue(Data<T> data) {
        this.data = data;
    }

    @Override
    public Queue<T> enQueue(T data) {
        return new ImmutableQueue<>(this.data.add(data));
    }

    @Override
    public Queue<T> deQueue() {
        return new ImmutableQueue<>(data.removeHead());
    }

    @Override
    public T head() {
        Data data = this.data;

        while (data.isNotHead()) {
            data = data.getPrevious();
        }

        return (T) data.getCurrent();
    }

    @Override
    public boolean isEmpty() {
        return data == null;
    }

    private class Data<T> {
        private T current;
        private Data<T> previous;

        Data(T current) {
            this.current = current;
        }

        private Data(T current, Data<T> previous) {
            this.current = current;
            this.previous = previous;
        }

        Data<T> add(T current) {
            return new Data<>(current, this);
        }

        Data<T> removeHead() {

            if (this.isHead()) {
                return null;
            }

            Data<T> data = this;
            Data<T> prev = this.getPrevious();
            T[] dataArray = (T[]) new Object[size()];
            int counter = 0;
            dataArray[counter] = data.current;

            while (prev.isNotHead()) {
                data = prev;
                prev = data.previous;
                counter++;
                dataArray[counter] = data.current;
            }

            Data<T> newData = null;

            for (int i = dataArray.length - 1; 0 <= i; i--) {
                if (i == dataArray.length - 1) {
                    newData = new Data<>(dataArray[i]);
                } else {
                    newData = new Data<>(dataArray[i], newData);
                }
            }
            return newData;
        }

        boolean isHead() {
            return this.previous == null;
        }

        boolean isNotHead() {
            return this.previous != null;
        }

        int size() {
            int size = 1;
            Data<T> data = previous;
            while (data.isNotHead()) {
                data = data.previous;
                size++;
            }
            return size;
        }

        T getCurrent() {
            return current;
        }

        Data<T> getPrevious() {
            return previous;
        }
    }
}
