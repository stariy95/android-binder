# Android Binder
This project is a simple annotation based model to view binder.

## Simple Injection
```java
class MyActivity extends Activity {
    @Inject(R.id.textView)
    TextView text;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_test);
        BindingContext.register(this);
        
        test.setText("Hello World");
    }
}
```

## Binding and callbacks
```java
class MyModel extends Observable {

    @Bind(R.id.editText)
    String text = "World!";
    
    @Bind(R.id.textView)
    String description = "Hello";
    
    @Inject(R.id.button)
    private Button button;
    
    public void setText(String text) {
        this.text = text;
        notifyFieldChange("text");
    }
    
    public void setDescription(String description) {
        this.description = description;
        notifyFieldChange("description");
    }
    
    @Callback(R.id.checkBox)
    public void setEnabled(boolean enabled) {
        button.setEnabled(enabled);
    }
}

class MyActivity extends Activity {
    @Model
    MyModel model;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_test);
        BindingContext.register(this);
        
        model.setEnabled(false);
    }
    
    @OnClick(R.id.button)
    void onTestButtonClick() {
        model.setDescription("Hello World!");
    }
    
}
```

## List View Helper
 
```java
class TestListModelHelper extends ViewHolderHelper<TestListModel> {                 
    @Inject(R.id.textViewName)
    TextView nameView;

    @Inject(R.id.textViewDescription)     
    TextView descriptionView;
 
    @Override
    public void applyView(TestListModel model, View rootView) {
        nameView.setText(model.name);
        descriptionView.setText(model.description);
    }
}  

class MyActivity extends Activity {
    
    List<TestListModelHelper> listModel = new ArrayList<>();
    
    @Inject(R.id.listView)
    ListView listView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_test);
        BindingContext.register(this);
        
        ...
        
        listView.setAdapter(new ListViewAdapter<>(this, R.layout.list_item_test, TestListBinder.class, listModel));
    }
}
```
