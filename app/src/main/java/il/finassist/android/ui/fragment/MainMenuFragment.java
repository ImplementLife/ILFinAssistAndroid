package il.finassist.android.ui.fragment;

import android.widget.GridView;
import androidx.navigation.NavController;
import il.finassist.android.R;
import il.finassist.android.ui.custom.adapter.GridListAdapter;
import il.finassist.android.ui.custom.component.BaseView;
import il.finassist.android.ui.custom.component.NavFragment;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MainMenuFragment extends NavFragment {
    private GridView grid;

    public MainMenuFragment() {
        super(R.layout.fragment_main_menu);
    }

    @Override
    protected void init() {
        grid = findViewById(R.id.grid);
        List<MainMenuButtonDefinition> menuList = Arrays.stream(MainMenuButtonDefinition.values())
            .sorted(Comparator.comparing(MainMenuButtonDefinition::getNumInList))
            .collect(Collectors.toList());

        GridListAdapter<MainMenuButtonDefinition> adapter = new GridListAdapter<>(menuList, (data, parent) -> {
            BaseView view = new BaseView(R.layout.view_btn_main_menu, grid);

            view.setOnClickListener(v -> data.getAction().accept(navController));
            view.setTextViewById(R.id.tv_name, data.getName());
            view.setImgResById(R.id.img_icon, data.getImgId());
            return view.getRoot();
        });
        grid.setAdapter(adapter);
    }

    public enum MainMenuButtonDefinition {
        BTN_SETTINGS("Settings", 0, R.drawable.ic_svg_cancel, n -> {
//            n.navigate(R.id.fragment_settings);
        }),
        BTN_CONTACTS("Contacts", 1, R.drawable.ic_svg_cancel, n -> {
//            n.navigate(R.id.fragment_contact);
        }),
        BTN_QR_SCANNER("QR", 2, R.drawable.ic_svg_cancel, n -> {
//            new ChooseQrAction(n).show();
        }),
        BTN_HISTORY("History", 3, R.drawable.ic_svg_cancel, n -> {
//            n.navigate(R.id.fragment_history);
        }),
        BTN_NOTIFICATIONS("Notify's", 4, R.drawable.ic_svg_cancel, n -> {
//            n.navigate(R.id.fragment_notify_list);
        }),
        BTN_NEW_SPLIT("Split", 5, R.drawable.ic_svg_cancel, n -> {
//            n.navigate(R.id.fragment_requisition_setup);
        }),
        BTN_BUDGET("Budget", 6, R.drawable.ic_svg_cancel, n -> {
//            n.navigate(R.id.fragment_budget_menu);
        }),
        BTN_NEW_TRANSACTION_BY_TEMPLATE("Templates", 7, R.drawable.ic_svg_cancel, n -> {
//            new ChooseTrnTemplateDialog().show();
        }),
        BTN_NEW_TRANSACTION("Transact", 8, R.drawable.ic_svg_cancel, n -> {
//            n.navigate(R.id.fragment_transaction_setup);
        }),
        ;

        MainMenuButtonDefinition(String name, int numInList, int imgId, Consumer<NavController> action) {
            this.name = name;
            this.imgId = imgId;
            this.action = action;
            this.numInList = numInList;
        }

        private final int numInList;
        private final String name;
        private final int imgId;
        private final Consumer<NavController> action;

        public int getNumInList() {
            return numInList;
        }
        public String getName() {
            return name;
        }
        public Consumer<NavController> getAction() {
            return action;
        }
        public int getImgId() {
            return imgId;
        }
    }
}